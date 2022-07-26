package ilya.chistousov.countcalories.domain.repository

import androidx.lifecycle.LiveData
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.util.Resource
import retrofit2.Response

interface FoodApiRepository {

    suspend fun getAllFood(): LiveData<Resource<List<Food>>>

    suspend fun getAllFoodByName(foodName: String): LiveData<Resource<List<Food>>>

    suspend fun getFoodById(id: Int): LiveData<Resource<Food>>
}