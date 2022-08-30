package ilya.chistousov.countthefood.food.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ilya.chistousov.countthefood.core.entity.ProfileEntity

@Dao
interface GetProfileDao {

    @Query("SELECT * FROM profile")
    fun getProfile() : LiveData<ProfileEntity>
}