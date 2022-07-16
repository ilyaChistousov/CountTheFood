package ilya.chistousov.countcalories

import android.app.Application
import android.content.Context
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.di.AppComponent
import ilya.chistousov.countcalories.di.DaggerAppComponent
import ilya.chistousov.countcalories.di.modeles.DataModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }