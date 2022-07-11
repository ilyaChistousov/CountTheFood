package ilya.chistousov.countcalories.domain.model

import java.util.*

data class Food(
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val proteins: Double,
    val fats: Double,
    val carbs: Double,
    val meal: Meal,
    val addedDate: Date
)

enum class Meal {
    BREAKFAST, LUNCH, DINNER, SNACK
}

