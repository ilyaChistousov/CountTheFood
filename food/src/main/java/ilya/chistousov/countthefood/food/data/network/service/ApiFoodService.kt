package ilya.chistousov.countthefood.food.data.network.service

import ilya.chistousov.countthefood.food.data.network.dto.FoodDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiFoodService {

    @GET("/food/{foodName}")
    suspend fun searchFoodByName(
        @Path("foodName") foodName: String) : Response<List<FoodDto>>
}