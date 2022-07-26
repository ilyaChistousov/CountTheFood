package ilya.chistousov.countcalories.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ilya.chistousov.countcalories.data.room.dao.FoodDao
import ilya.chistousov.countcalories.data.room.dao.ProfileDao
import ilya.chistousov.countcalories.data.room.entity.FoodEntity
import ilya.chistousov.countcalories.data.room.entity.ProfileEntity
import ilya.chistousov.countcalories.data.room.entity.typeconvrerter.DateConverter
import ilya.chistousov.countcalories.data.room.entity.typeconvrerter.LocalDateTimeConverter

@Database(entities = [FoodEntity::class, ProfileEntity::class],  version = 1)
@TypeConverters(LocalDateTimeConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun profileDao() : ProfileDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        const val DATABASE_NAME = "AppDatabase"

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