package ilya.chistousov.countcalories

import android.app.Application
import android.content.Context
import ilya.chistousov.countcalories.di.AppComponent
import ilya.chistousov.countcalories.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .context(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }