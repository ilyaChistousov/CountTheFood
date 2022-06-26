package ilya.chistousov.countcalories.domain.usecases

import ilya.chistousov.countcalories.domain.repository.FoodRepository

class GetAllFoodUseCase(private val repository: FoodRepository) {

    operator fun invoke() = repository.getAllFoods()
}