package ilya.chistousov.countthefood.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ilya.chistousov.counthefood.database.dao.FoodDao
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.room.typeconvrerter.DateConverter
import ilya.chistousov.countthefood.room.typeconvrerter.LocalDateTimeConverter

@Database(entities = [FoodEntity::class, ProfileEntity::class],  version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun profileDao() : ProfileDao
}