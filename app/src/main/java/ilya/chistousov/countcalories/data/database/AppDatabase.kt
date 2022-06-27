package ilya.chistousov.countcalories.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ilya.chistousov.countcalories.data.dao.FoodDao
import ilya.chistousov.countcalories.data.entity.FoodDbEntity

@Database(entities = [FoodDbEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "AppDatabase"

        @Synchronized
        fun getInstance(application: Application) : AppDatabase{
            var db = INSTANCE
            if (db == null) {
                db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = db
            }
            return db
        }
    }
}