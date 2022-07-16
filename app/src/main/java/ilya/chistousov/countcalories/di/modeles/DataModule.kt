package ilya.chistousov.countcalories.di.modeles

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ilya.chistousov.countcalories.data.database.AppDatabase
import javax.inject.Singleton

@Module
class DataModule(private val application: Application) {

    @Provides
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    @Provides
    fun provideFoodDao(database: AppDatabase) = database.foodDao()

    @Provides
    fun provideProfileDao(database: AppDatabase) = database.profileDao()
}