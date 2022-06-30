package ilya.chistousov.countcalories

import android.app.Application
import ilya.chistousov.countcalories.data.database.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}