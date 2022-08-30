package ilya.chistousov.countthefood.signup.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ilya.chistousov.countthefood.core.entity.ProfileEntity

@Dao
interface CreateProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProfile(profileDb: ProfileEntity)
}