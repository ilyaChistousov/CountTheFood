package ilya.chistousov.countcalories.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ilya.chistousov.countcalories.data.room.dao.FoodDao
import ilya.chistousov.countcalories.data.room.dao.ProfileDao
import ilya.chistousov.countcalories.data.entity.FoodDbEntity
import ilya.chistousov.countcalories.data.entity.ProfileDbEntity
import ilya.chistousov.countcalories.data.entity.typeconvrerter.DateConverter
import ilya.chistousov.countcalories.data.entity.typeconvrerter.LocalDateTimeConverter

@Database(entities = [FoodDbEntity::class, ProfileDbEntity::class],  version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun profileDao() : ProfileDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "AppDatabase"

        @Synchronized
        fun getInstance(context: Context) : AppDatabase{
            var db = INSTANCE
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = db
            }
            return db
        }
    }
}