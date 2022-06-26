package ilya.chistousov.countcalories.domain.model

data class Food(
    val id: Int,
    val name: String,
    val calories: Double,
    val protein: Double,
    val fat: Double,
    val carbs: Double
)
