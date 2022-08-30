package ilya.chistousov.countthefood.signup.di

import androidx.lifecycle.ViewModel

internal class SignUpComponentViewModel : ViewModel() {

    val signUpComponent = DaggerSignUpComponent.factory().create(SignUpDepsProvider.deps)
}