package ilya.chistousov.countcalories.data.network.service

import androidx.lifecycle.LiveData
import ilya.chistousov.countcalories.data.network.dto.FoodDto
import ilya.chistousov.countcalories.domain.model.Food
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiFoodService {

    @GET("$BASE/foods")
    suspend fun getAllFood() : Response<List<FoodDto>>

    @GET("$BASE/foods/{foodName}")
    suspend fun getAllFoodByName(
        @Path("foodName") foodName: String) : Response<List<FoodDto>>

    @GET("$BASE/food/{id}")
    suspend fun getFoodById(
        @Path("id") id: Int) : Response<FoodDto>

    companion object {
        private const val BASE = "api/v0"
    }
}