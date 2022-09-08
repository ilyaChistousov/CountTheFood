package ilya.chistousov.countthefood.api.service

import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import retrofit2.Response
import retrofit2.http.*

interface ProfileService {

    @POST("/profile/create")
    suspend fun createProfile(@Body profile: ProfileDto)

    @GET("/profile/{email}")
    suspend fun getProfileByEmail(@Path("email") email: String) : Response<ProfileDto>

    @PATCH("/profile/{email}")
    suspend fun addFoodToProfile(@Path("email") email: String, @Body profileFoodDto: ProfileFoodDto)
}