package ilya.chistousov.countcalories.presentation.meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.usecases.food.AddFoodUseCase
import ilya.chistousov.countcalories.domain.usecases.food.DeleteFoodUseCase
import ilya.chistousov.countcalories.domain.usecases.food.GetFoodUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFoodViewModel @Inject constructor(
    private val addFoodUseCase: AddFoodUseCase,
    private val getFoodUseCase: GetFoodUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase) : ViewModel() {

    fun addFoodToDb(food: Food) = viewModelScope.launch {
        val savedFood = getFoodUseCase(food.id)
        if(savedFood != null) {
            val updateFood = Food(
                id = savedFood.id,
                food.name,
                food.calories,
                food.protein,
                food.fat,
                food.carbs,
                food.gram,
                food.meal,
                food.addedDate
            )
            addFoodUseCase(updateFood)
        }
        else {
            addFoodUseCase(food)
        }
    }

    fun deleteFood(food: Food) = viewModelScope.launch {
        deleteFoodUseCase(food)
    }

}