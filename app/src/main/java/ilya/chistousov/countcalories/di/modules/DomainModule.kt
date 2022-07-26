package ilya.chistousov.countcalories.di.modules

import dagger.Binds
import dagger.Module
import ilya.chistousov.countcalories.data.firebase.repository.RegisterAccountRepositoryImpl
import ilya.chistousov.countcalories.data.network.repository.FoodApiRepositoryImpl
import ilya.chistousov.countcalories.data.room.repository.FoodRepositoryImpl
import ilya.chistousov.countcalories.data.room.repository.ProfileRepositoryImpl
import ilya.chistousov.countcalories.domain.repository.FoodApiRepository
import ilya.chistousov.countcalories.domain.repository.FoodRepository
import ilya.chistousov.countcalories.domain.repository.ProfileRepository
import ilya.chistousov.countcalories.domain.repository.RegisterAccountRepository

@Module
interface DomainModule {

    @Binds
    fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository

    @Binds
    fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun bindRegisterAccountRepository(
        registerAccountRepositoryImpl: RegisterAccountRepositoryImpl
    ): RegisterAccountRepository

    @Binds
    fun bindFoodApiRepository(
        foodApiRepository: FoodApiRepositoryImpl
    ) : FoodApiRepository
}