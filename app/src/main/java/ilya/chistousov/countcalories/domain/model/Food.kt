package ilya.chistousov.countcalories.domain.model

data class Food(
    val id: Int,
    val name: String,
    val calories: Double,
    val proteins: Double,
    val fats: Double,
    val carbs: Double,
    val meal: Meal
)
