package ilya.chistousov.countthefood.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ilya.chistousov.countthefood.core.entity.ProfileEntity
import ilya.chistousov.countthefood.data.room.typeconvrerter.DateConverter
import ilya.chistousov.countthefood.data.room.typeconvrerter.LocalDateTimeConverter
import ilya.chistousov.countthefood.food.data.dao.FoodDao
import ilya.chistousov.countthefood.food.data.dao.GetProfileDao
import ilya.chistousov.countthefood.food.data.entity.FoodEntity
import ilya.chistousov.countthefood.signup.data.dao.CreateProfileDao

@Database(entities = [FoodEntity::class, ProfileEntity::class],  version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun createProfileDao() : CreateProfileDao
    abstract fun getProfileDao() : GetProfileDao
}