package ilya.chistousov.countcalories.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ilya.chistousov.countcalories.di.modules.DataModule
import ilya.chistousov.countcalories.di.modules.DomainModule
import ilya.chistousov.countcalories.di.modules.NetworkModule
import ilya.chistousov.countcalories.di.modules.PresentationModule
import ilya.chistousov.countcalories.presentation.diary.fragment.DiaryFragment
import ilya.chistousov.countcalories.presentation.meal.fragment.MealFragment
import ilya.chistousov.countcalories.presentation.meal.screen.SearchFoodFragment
import ilya.chistousov.countcalories.presentation.register.fragment.RegisterFragment
import ilya.chistousov.countcalories.presentation.register.screen.*
import ilya.chistousov.countcalories.presentation.viewmodelfactory.MultiViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, NetworkModule::class, PresentationModule::class])
interface AppComponent {

    fun inject(registerFragment: RegisterFragment)
    fun inject(activityLevelScreen: ActivityLevelScreen)
    fun inject(birthDateScreen: BirthDateScreen)
    fun inject(currentGrowthScreen: CurrentGrowthScreen)
    fun inject(currentWeightScreen: CurrentWeightScreen)
    fun inject(desiredWeightScreen: DesiredWeightScreen)
    fun inject(genderScreen: GenderScreen)
    fun inject(goalScreen: GoalScreen)
    val factory: MultiViewModelFactory

    @Component.Factory
    interface Factory {
        fun context(@BindsInstance context: Context) : AppComponent
    }
}