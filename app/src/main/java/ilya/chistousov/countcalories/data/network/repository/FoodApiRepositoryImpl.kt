package ilya.chistousov.countcalories.data.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ilya.chistousov.countcalories.data.network.mapper.FoodDtoListMapper
import ilya.chistousov.countcalories.data.network.mapper.FoodDtoMapper
import ilya.chistousov.countcalories.data.network.service.ApiFoodService
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.repository.FoodApiRepository
import ilya.chistousov.countcalories.util.Resource
import ilya.chistousov.countcalories.util.handleResponse
import kotlinx.coroutines.*
import javax.inject.Inject

class FoodApiRepositoryImpl @Inject constructor(
    private val dtoMapper: FoodDtoMapper,
    private val listDtoMapper: FoodDtoListMapper,
    private val apiFoodService: ApiFoodService
) : FoodApiRepository {

    override suspend fun getAllFood(): LiveData<Resource<List<Food>>> {
        return returnLiveDataWithResources(
            apiFoodService.getAllFood().handleResponse(listDtoMapper)
        )
    }

    override suspend fun  getAllFoodByName(foodName: String): LiveData<Resource<List<Food>>> {
       return returnLiveDataWithResources(
            apiFoodService.getAllFoodByName(foodName).handleResponse(listDtoMapper)
        )
    }

    override suspend fun getFoodById(id: Int): LiveData<Resource<Food>> {
        return returnLiveDataWithResources(
            apiFoodService.getFoodById(id).handleResponse(dtoMapper)
        )
    }

    private fun <T> returnLiveDataWithResources(resource: Resource<T>): LiveData<Resource<T>> {
        return liveData {
            emit(Resource.Loading(true))
            emit(resource)
        }
    }
}