package ilya.chistousov.countcalories.presentation.util

import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal

fun List<Food>.filterListFoodByMeal(meal:Meal) : List<Food> {
    return this.filter { it.meal == meal }
}