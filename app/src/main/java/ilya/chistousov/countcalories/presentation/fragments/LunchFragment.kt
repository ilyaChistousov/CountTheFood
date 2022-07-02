package ilya.chistousov.countcalories.presentation.fragments

import ilya.chistousov.countcalories.domain.model.Meal

class LunchFragment : BaseMealFragment() {
    override val meal: Meal
        get() = Meal.LUNCH
}