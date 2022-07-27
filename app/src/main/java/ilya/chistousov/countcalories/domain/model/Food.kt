package ilya.chistousov.countcalories.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Food(
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val gram:Int = 100,
    val meal: Meal = Meal.NONE,
    val addedDate: LocalDate
) : Parcelable

enum class Meal {
    BREAKFAST, LUNCH, DINNER, SNACK, NONE
}

