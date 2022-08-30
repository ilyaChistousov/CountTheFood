package ilya.chistousov.countthefood.food.util

import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import java.time.LocalDate

fun List<Food>.filterListFoodByMealAndDate(meal: Meal, localDate: LocalDate): List<Food> {
    return filter { it.meal == meal }
        .filter { it.addedDate == localDate }
}


fun List<Food>.filterListFoodByDate(currentDate: LocalDate): List<Food> {
    return filter { it.addedDate == currentDate }
}