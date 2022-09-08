package ilya.chistousov.countthefood

import android.app.Application
import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ilya.chistousov.counthefood.database.dao.FoodDao
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import ilya.chistousov.countthefood.api.service.FoodService
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.di.AppComponent
import ilya.chistousov.countthefood.di.DaggerAppComponent
import ilya.chistousov.countthefood.food.di.FoodDeps
import ilya.chistousov.countthefood.food.di.FoodDepsProvider
import ilya.chistousov.countthefood.mapper.*
import ilya.chistousov.countthefood.signin.di.SignInDeps
import ilya.chistousov.countthefood.signin.di.SignInDepsProvider
import ilya.chistousov.countthefood.signup.di.SignUpDeps
import ilya.chistousov.countthefood.signup.di.SignUpDepsProvider

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .context(this)
    }

    override fun onCreate() {
        super.onCreate()
        provideSignUpModuleDeps()
        provideSignInModuleDeps()
        provideFoodModuleDeps()
    }

    private fun provideFoodModuleDeps() {
        FoodDepsProvider.deps = object : FoodDeps {
            override val foodDao: FoodDao
                get() = appComponent.database.foodDao()
            override val profileDao: ProfileDao
                get() = appComponent.database.profileDao()
            override val foodService: FoodService
                get() = appComponent.foodService
            override val profileService: ProfileService
                get() = appComponent.profileService
            override val fragmentContainerId: Int
                get() = R.id.fragmentContainer
            override val foodMapper: BaseMapper<FoodEntity, Food>
                get() = FoodEntityMapper()
            override val profileMapper: BaseMapper<ProfileEntity, Profile>
                get() = ProfileEntityMapper()
            override val profileFoodDtoMapper: BaseMapper<ProfileFoodDto, Food>
                get() = ProfileFoodDtoMapper()
        }
    }

    private fun provideSignInModuleDeps() {
        SignInDepsProvider.deps = object : SignInDeps {
            override val auth: FirebaseAuth
                get() = Firebase.auth
            override val googleSignInClient: GoogleSignInClient
                get() = appComponent.googleSignInClient
            override val profileDao: ProfileDao
                get() = appComponent.database.profileDao()
            override val profileEntityMapper: BaseMapper<ProfileEntity, Profile>
                get() = ProfileEntityMapper()
            override val profileService: ProfileService
                get() = appComponent.profileService
            override val foodDao: FoodDao
                get() = appComponent.database.foodDao()
            override val foodEntityToProfileFoodDtoMapper: BaseMapper<FoodEntity, ProfileFoodDto>
                get() = FoodEntityToProfileFoodDtoMapper()
        }
    }

    private fun provideSignUpModuleDeps() {
        SignUpDepsProvider.deps = object : SignUpDeps {
            override val auth: FirebaseAuth
                get() = Firebase.auth
            override val googleClient: GoogleSignInClient
                get() = appComponent.googleSignInClient
            override val sharedPreferences: SharedPreferences
                get() = appComponent.sharedPreferences
            override val profileDao: ProfileDao
                get() = appComponent.database.profileDao()
            override val profileService: ProfileService
                get() = appComponent.profileService
            override val profileMapper: BaseMapper<ProfileEntity, Profile>
                get() = ProfileEntityMapper()
            override val profileDtoMapper: BaseMapper<ProfileDto, Profile>
                get() = ProfileDtoMapper()
        }
    }
}