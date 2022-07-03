package ilya.chistousov.countcalories.domain.model

data class Food(
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val proteins: Double,
    val fats: Double,
    val carbs: Double,
    val meal: Meal
)
