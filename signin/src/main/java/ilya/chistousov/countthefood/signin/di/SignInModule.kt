package ilya.chistousov.countthefood.signin.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ilya.chistousov.countthefood.core.viewmodelfactory.ViewModelKey
import ilya.chistousov.countthefood.signin.data.repository.SignInRepositoryImpl
import ilya.chistousov.countthefood.signin.domain.repository.SignInRepository
import ilya.chistousov.countthefood.signin.presentation.viewmodel.SignInViewModel

@Module
interface SignInModule {

    @Binds
    fun bindSignInRepository(signInRepositoryImpl: SignInRepositoryImpl) : SignInRepository

    @Binds
    @[IntoMap ViewModelKey(value = SignInViewModel::class)]
    fun bindSignInViewModel(signInViewModel: SignInViewModel) : ViewModel
}