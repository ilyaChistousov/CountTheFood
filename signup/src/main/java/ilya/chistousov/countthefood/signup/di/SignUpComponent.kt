package ilya.chistousov.countthefood.signup.di

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Component
import ilya.chistousov.countthefood.core.viewmodelfactory.MultiViewModelFactory
import ilya.chistousov.countthefood.signup.data.dao.CreateProfileDao
import ilya.chistousov.countthefood.signup.presentation.viewmodel.CreateProfileViewModel
import kotlin.properties.Delegates.notNull

@Component(
    modules = [SignUpModule::class],
    dependencies = [SignUpDeps::class]
)
internal interface SignUpComponent {

    val factory : MultiViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(deps: SignUpDeps) : SignUpComponent
    }
}

interface SignUpDeps {
    val auth: FirebaseAuth
    val googleClient: GoogleSignInClient
    val sharedPreferences: SharedPreferences
    val dao: CreateProfileDao
    val context: Context
}

object SignUpDepsProvider {
    var deps: SignUpDeps by notNull()
}