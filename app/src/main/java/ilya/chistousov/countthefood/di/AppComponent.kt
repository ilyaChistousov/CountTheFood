package ilya.chistousov.countthefood.di

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.BindsInstance
import dagger.Component
import ilya.chistousov.countthefood.data.room.database.AppDatabase
import ilya.chistousov.countthefood.di.modules.FireBaseModule
import ilya.chistousov.countthefood.di.modules.LocalDataStore
import ilya.chistousov.countthefood.di.modules.NetworkModule
import ilya.chistousov.countthefood.food.data.network.service.ApiFoodService
import javax.inject.Singleton

@Singleton
@Component(modules = [LocalDataStore::class, NetworkModule::class, FireBaseModule::class])
interface AppComponent {

    val database: AppDatabase
    val googleSignInClient: GoogleSignInClient
    val sharedPreferences: SharedPreferences
    val apiFoodService: ApiFoodService

    @Component.Factory
    interface Factory {
        fun context(@BindsInstance context: Context) : AppComponent
    }
}