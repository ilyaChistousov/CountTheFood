package ilya.chistousov.countcalories.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ilya.chistousov.countcalories.data.room.database.AppDatabase
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    @Provides
    fun provideFoodDao(database: AppDatabase) = database.foodDao()

    @Provides
    fun provideProfileDao(database: AppDatabase) = database.profileDao()

    @Provides
    fun provideSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "Profile"
    }

}