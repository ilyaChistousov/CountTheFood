package ilya.chistousov.countthefood.api.service

import ilya.chistousov.countthefood.api.dto.FoodDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodService {

    @GET("/food/{foodName}")
    suspend fun searchFoodByName(@Path("foodName") foodName: String) : Response<List<FoodDto>>
}