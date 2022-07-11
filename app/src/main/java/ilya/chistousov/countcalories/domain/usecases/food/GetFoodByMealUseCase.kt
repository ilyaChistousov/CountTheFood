package ilya.chistousov.countcalories.domain.usecases.food

import androidx.lifecycle.LiveData
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.repository.FoodRepository

class GetFoodByMealUseCase(private val repository: FoodRepository) {

    operator fun invoke(meal: Meal) : LiveData<List<Food>> {
       return repository.getFoodByMeal(meal)
    }
}