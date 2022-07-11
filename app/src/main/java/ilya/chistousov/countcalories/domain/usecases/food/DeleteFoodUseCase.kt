package ilya.chistousov.countcalories.domain.usecases.food

import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.repository.FoodRepository

class DeleteFoodUseCase(private val repository: FoodRepository) {

    suspend operator fun invoke(food: Food) = repository.deleteFood(food)

}