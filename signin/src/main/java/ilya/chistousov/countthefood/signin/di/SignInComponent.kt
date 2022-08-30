package ilya.chistousov.countthefood.signin.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Component
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
    val context: Context
    val auth: FirebaseAuth
    val googleSignInClient: GoogleSignInClient
}

object SignInDepsProvider {
    var deps: SignInDeps by notNull()
}
