package ilya.chistousov.countcalories.domain.model

import java.time.LocalDate

data class Food(
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val meal: Meal = Meal.NONE,
    val addedDate: LocalDate
)

enum class Meal {
    BREAKFAST, LUNCH, DINNER, SNACK, NONE
}

