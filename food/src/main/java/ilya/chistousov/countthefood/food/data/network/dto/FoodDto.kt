package ilya.chistousov.countthefood.food.data.network.dto

data class FoodDto(
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val grams: Int
)
