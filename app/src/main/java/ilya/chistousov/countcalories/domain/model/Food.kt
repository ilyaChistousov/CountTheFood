package ilya.chistousov.countcalories.domain.model

import java.time.LocalDate

data class Food(
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val proteins: Float,
    val fats: Float,
    val carbs: Float,
    val meal: Meal,
    val addedDate: LocalDate
)

enum class Meal {
    BREAKFAST, LUNCH, DINNER, SNACK
}

