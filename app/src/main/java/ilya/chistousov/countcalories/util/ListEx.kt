package ilya.chistousov.countcalories.util

import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import java.time.LocalDate

fun List<Food>.filterListFoodByMealAndDate(meal: Meal, localDate: LocalDate): List<Food> {
    return filter { it.meal == meal }
        .filter { it.addedDate == localDate }
}


fun List<Food>.filterListFoodByDate(currentDate: LocalDate): List<Food> {
    return filter { it.addedDate == currentDate }
}