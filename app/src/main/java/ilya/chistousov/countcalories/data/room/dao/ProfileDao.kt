package ilya.chistousov.countcalories.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ilya.chistousov.countcalories.data.room.entity.ProfileEntity

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProfile(profileDb: ProfileEntity)

    @Query("SELECT * FROM profile")
    fun getProfile() : LiveData<ProfileEntity>
}