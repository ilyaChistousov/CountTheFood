package ilya.chistousov.countcalories.di.modules

import android.content.Context
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
}