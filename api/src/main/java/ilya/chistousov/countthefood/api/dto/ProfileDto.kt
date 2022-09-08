package ilya.chistousov.countthefood.api.dto

data class ProfileDto(
    val gender: String,
    val goal: String,
    val birthDate: String,
    val activityLevel: String,
    val currentGrowth: Int,
    val currentWeight: Float,
    val desiredWeight: Float,
    val email: String,
    val foods: List<ProfileFoodDto> = arrayListOf()
)
