package ilya.chistousov.countcalories.data.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.mapper.ProfileMapper
import ilya.chistousov.countcalories.data.room.dao.FoodDao
import ilya.chistousov.countcalories.data.room.dao.ProfileDao
import ilya.chistousov.countcalories.domain.model.Profile
import ilya.chistousov.countcalories.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dao: ProfileDao,
    private val mapper: ProfileMapper
) : ProfileRepository {

    override suspend fun createProfile(profile: Profile) {
        dao.createProfile(mapper.mapFromModelToDbEntity(profile))
    }

    override fun getProfile(): LiveData<Profile> {
        return Transformations.map(dao.getProfile())
        { mapper.mapFromDbEntityToModel(it) }
    }
}