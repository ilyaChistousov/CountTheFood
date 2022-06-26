package ilya.chistousov.countcalories.domain.usecases

import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.repository.FoodRepository

class AddFoodUseCase(private val repository: FoodRepository) {

    suspend operator fun invoke(food: Food) = repository.addFood(food)
}