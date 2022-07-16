package ilya.chistousov.countcalories.domain.usecases.food

import ilya.chistousov.countcalories.domain.repository.FoodRepository
import javax.inject.Inject

class GetFoodUseCase @Inject constructor(private val repository: FoodRepository) {

    operator fun invoke(foodId: Int) = repository.getFood(foodId)
}