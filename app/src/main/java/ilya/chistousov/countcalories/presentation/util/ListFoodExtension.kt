package ilya.chistousov.countcalories.presentation.util

import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import java.util.*

fun List<Food>.filterListFoodByMealAndDate(meal: Meal, date: Date) : List<Food> {
    return this.filter { it.meal == meal }
        .filter { it.addedDate == date }
}