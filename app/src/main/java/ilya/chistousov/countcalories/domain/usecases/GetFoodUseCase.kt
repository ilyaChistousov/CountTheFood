package ilya.chistousov.countcalories.domain.usecases

import ilya.chistousov.countcalories.domain.repository.FoodRepository

class GetFoodUseCase(private val repository: FoodRepository) {

    operator fun invoke(foodId: Int) = repository.getFood(foodId)
}