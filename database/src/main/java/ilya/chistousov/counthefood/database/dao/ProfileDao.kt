package ilya.chistousov.counthefood.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ilya.chistousov.counthefood.database.entity.ProfileEntity

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProfile(profileDb: ProfileEntity)

    @Query("SELECT * FROM profile LIMIT 1")
    fun getProfile() : LiveData<ProfileEntity>
}