package ilya.chistousov.countthefood.signin.di

import androidx.lifecycle.ViewModel

internal class SignInComponentViewModel : ViewModel() {

    val signInComponent = DaggerSignInComponent.factory().create(SignInDepsProvider.deps)
}