package ilya.chistousov.countthefood.api.dto

data class FoodDto(
    val name: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val grams: Int
)