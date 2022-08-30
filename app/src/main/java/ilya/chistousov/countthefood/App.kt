package ilya.chistousov.countthefood

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ilya.chistousov.countthefood.di.AppComponent
import ilya.chistousov.countthefood.di.DaggerAppComponent
import ilya.chistousov.countthefood.food.data.dao.FoodDao
import ilya.chistousov.countthefood.food.data.dao.GetProfileDao
import ilya.chistousov.countthefood.food.data.network.service.ApiFoodService
import ilya.chistousov.countthefood.food.di.FoodDeps
import ilya.chistousov.countthefood.food.di.FoodDepsProvider
import ilya.chistousov.countthefood.presentation.MainActivity
import ilya.chistousov.countthefood.signin.di.SignInDeps
import ilya.chistousov.countthefood.signin.di.SignInDepsProvider
import ilya.chistousov.countthefood.signup.data.dao.CreateProfileDao
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
        SignInDepsProvider.deps = object : SignInDeps {
            override val context: Context
                get() = this@App
            override val auth: FirebaseAuth
                get() = Firebase.auth
            override val googleSignInClient: GoogleSignInClient
                get() = appComponent.googleSignInClient
        }

        SignUpDepsProvider.deps = object : SignUpDeps {
            override val auth: FirebaseAuth
                get() = Firebase.auth
            override val googleClient: GoogleSignInClient
                get() = appComponent.googleSignInClient
            override val sharedPreferences: SharedPreferences
                get() = appComponent.sharedPreferences
            override val dao: CreateProfileDao
                get() = appComponent.database.createProfileDao()
            override val context: Context
                get() = this@App
        }

        FoodDepsProvider.deps = object : FoodDeps {
            override val foodDao: FoodDao
                get() = appComponent.database.foodDao()
            override val getProfileDao: GetProfileDao
                get() = appComponent.database.getProfileDao()
            override val apiFoodService: ApiFoodService
                get() = appComponent.apiFoodService
            override val fragmentContainerId: Int
                get() = R.id.fragmentContainer
        }
    }
}