package ilya.chistousov.countthefood.signin.di

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Component
import ilya.chistousov.counthefood.database.dao.FoodDao
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.core.viewmodelfactory.MultiViewModelFactory
import kotlin.properties.Delegates.notNull

@Component(
    modules = [SignInModule::class],
    dependencies = [SignInDeps::class]
)
internal interface SignInComponent {

    val factory: MultiViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: SignInDeps): SignInComponent
    }
}

interface SignInDeps {
    val auth: FirebaseAuth
    val googleSignInClient: GoogleSignInClient
    val profileDao: ProfileDao
    val profileEntityMapper: BaseMapper<ProfileEntity, Profile>
    val profileService: ProfileService
    val foodDao: FoodDao
    val foodEntityToProfileFoodDtoMapper: BaseMapper<FoodEntity, ProfileFoodDto>
}

object SignInDepsProvider {
    var deps: SignInDeps by notNull()
}
