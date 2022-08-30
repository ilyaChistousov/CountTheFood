package ilya.chistousov.countthefood.food.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.food.data.dao.GetProfileDao
import ilya.chistousov.countthefood.food.data.mapper.ProfileMapper
import ilya.chistousov.countthefood.food.domain.repository.GetProfileRepository
import javax.inject.Inject

class GetProfileRepositoryImpl @Inject constructor(
    private val dao: GetProfileDao,
    private val mapper: ProfileMapper
) : GetProfileRepository {

    override fun getProfile(): LiveData<Profile> {
        return Transformations.map(dao.getProfile())
        { mapper.mapFromEntityToModel(it) }
    }
}