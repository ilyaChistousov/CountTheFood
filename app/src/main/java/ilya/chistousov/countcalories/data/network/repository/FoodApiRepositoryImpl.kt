package ilya.chistousov.countcalories.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ilya.chistousov.countcalories.data.network.mapper.FoodDtoListMapper
import ilya.chistousov.countcalories.data.network.mapper.FoodDtoMapper
import ilya.chistousov.countcalories.data.network.service.ApiFoodService
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.repository.FoodApiRepository
import ilya.chistousov.countcalories.util.Response
import ilya.chistousov.countcalories.util.handleResponse
import javax.inject.Inject

class FoodApiRepositoryImpl @Inject constructor(
    private val dtoMapper: FoodDtoMapper,
    private val listDtoMapper: FoodDtoListMapper,
    private val apiFoodService: ApiFoodService
) : FoodApiRepository {

    override suspend fun getAllFood(): LiveData<Response<List<Food>>> {
        return returnLiveDataWithResources(
            apiFoodService.getAllFood().handleResponse(listDtoMapper)
        )
    }

    override suspend fun  getAllFoodByName(foodName: String): LiveData<Response<List<Food>>> {
       return returnLiveDataWithResources(
            apiFoodService.getAllFoodByName(foodName).handleResponse(listDtoMapper)
        )
    }

    override suspend fun getFoodById(id: Int): LiveData<Response<Food>> {
        return returnLiveDataWithResources(
            apiFoodService.getFoodById(id).handleResponse(dtoMapper)
        )
    }

    private fun <T> returnLiveDataWithResources(response: Response<T>): LiveData<Response<T>> {
        return liveData {
            emit(Response.Loading(true))
            emit(response)
        }
    }
}