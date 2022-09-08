package ilya.chistousov.countthefood.signin.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import ilya.chistousov.counthefood.database.dao.FoodDao
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import ilya.chistousov.countthefood.api.result.Result
import ilya.chistousov.countthefood.api.result.handleResponse
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.signin.data.mapper.ProfileResponseMapper
import ilya.chistousov.countthefood.signin.domain.repository.SignInRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val googleClient: GoogleSignInClient,
    private val profileService: ProfileService,
    private val profileResponseMapper: ProfileResponseMapper,
    private val profileEntityMapper: BaseMapper<ProfileEntity, Profile>,
    private val profileDao: ProfileDao,
    private val foodDao: FoodDao,
    private val foodEntityToProfileFoodDtoMapper: BaseMapper<FoodEntity, ProfileFoodDto>,
) : SignInRepository {

    override suspend fun signInWithGoogle(
        idToken: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.result.additionalUserInfo!!.isNewUser) {
                it.result.user!!.delete()
                googleClient.signOut()
                auth.signOut()
                return@addOnCompleteListener onFailure()
            }
            onSuccess()
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                onSuccess()
            }
        }.addOnFailureListener {
            onFailure()
        }
    }

    override suspend fun resetPassword(
        email: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            onFailure()
        }
    }

    override suspend fun createProfile(email: String) {
        val result = profileService.getProfileByEmail(email).handleResponse()
        if (result is Result.Success) {
            val map = result.map(profileResponseMapper)
            val listFoodDto = result.data!!.foods
            val listFoodEntity = listFoodDto.map {
                foodEntityToProfileFoodDtoMapper.mapFromModelToEntity(it)
            }
            foodDao.addListFood(listFoodEntity)
            val profileEntity = profileEntityMapper.mapFromModelToEntity(map)
            profileDao.createProfile(profileEntity)
        }
    }
}