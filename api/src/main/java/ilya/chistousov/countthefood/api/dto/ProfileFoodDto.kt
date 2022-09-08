package ilya.chistousov.countthefood.api.dto

data class ProfileFoodDto(
    val name: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val grams: Int,
    val addedDate: String,
    val meal: String
)
