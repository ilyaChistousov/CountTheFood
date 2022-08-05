package ilya.chistousov.countcalories.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ilya.chistousov.countcalories.di.modules.*
import ilya.chistousov.countcalories.presentation.viewmodelfactory.MultiViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, NetworkModule::class,
    PresentationModule::class, FireBaseModule::class])
interface AppComponent {

    val factory: MultiViewModelFactory

    @Component.Factory
    interface Factory {
        fun context(@BindsInstance context: Context) : AppComponent
    }
}