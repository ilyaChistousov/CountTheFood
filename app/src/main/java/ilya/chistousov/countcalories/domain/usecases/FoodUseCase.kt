package ilya.chistousov.countcalories.domain.usecases

import ilya.chistousov.countcalories.domain.repository.FoodRepository

class FoodUseCase(repository: FoodRepository) {

    val addFoodUseCase = AddFoodUseCase(repository)
    val deleteFoodUseCase = DeleteFoodUseCase(repository)
    val getAllFoodUseCase = GetAllFoodUseCase(repository)
    val getFoodUseCase = GetFoodUseCase(repository)
    val getFoodByMealUseCase = GetFoodByMealUseCase(repository)
}