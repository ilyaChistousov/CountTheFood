package ilya.chistousov.countthefood.signup.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ilya.chistousov.countthefood.core.viewmodelfactory.ViewModelKey
import ilya.chistousov.countthefood.signup.data.repository.CreateProfileRepositoryImpl
import ilya.chistousov.countthefood.signup.data.repository.SignUpRepositoryImpl
import ilya.chistousov.countthefood.signup.domain.repository.CreateProfileRepository
import ilya.chistousov.countthefood.signup.domain.repository.SignUpRepository
import ilya.chistousov.countthefood.signup.presentation.viewmodel.CreateProfileViewModel
import ilya.chistousov.countthefood.signup.presentation.viewmodel.SignUpViewModel

@Module
interface SignUpModule {

    @Binds
    fun bindCreateProfileRepository(createProfileRepositoryImpl: CreateProfileRepositoryImpl) : CreateProfileRepository

    @Binds
    fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl) : SignUpRepository

    @Binds
    @[IntoMap ViewModelKey(CreateProfileViewModel::class)]
    fun bindCreateProfileViewModel(createProfileViewModel: CreateProfileViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(SignUpViewModel::class)]
    fun bindSignUpViewModel(signUpViewModel: SignUpViewModel) : ViewModel
}