package ilya.chistousov.countcalories.data.network.dto

data class FoodDto(
    val id: Int,
    val name: String,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbohydrate: Float
)