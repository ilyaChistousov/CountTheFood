package ilya.chistousov.countthefood.food.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.food.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dao: ProfileDao,
    private val mapper: BaseMapper<ProfileEntity, Profile>
) : ProfileRepository {

    override fun getProfile(): LiveData<Profile> {
        return Transformations.map(dao.getProfile())
        { mapper.mapFromEntityToModel(it) }
    }
}