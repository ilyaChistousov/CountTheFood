package ilya.chistousov.countcalories.domain.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthCredential
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.util.Response

interface FoodApiRepository {

    suspend fun getAllFood(): LiveData<Response<List<Food>>>

    suspend fun getAllFoodByName(foodName: String): LiveData<Response<List<Food>>>

    suspend fun getFoodById(id: Int): LiveData<Response<Food>>
}