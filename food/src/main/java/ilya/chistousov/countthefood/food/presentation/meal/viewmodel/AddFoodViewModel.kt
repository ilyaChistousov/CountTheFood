package ilya.chistousov.countthefood.food.presentation.meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.food.domain.repository.FoodRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    fun addFood(food: Food) = viewModelScope.launch {
        val savedFood = foodRepository.getFood(food.id)
        if (savedFood != null) {
            val updateFood = Food(
                savedFood.id,
                food.name,
                food.calories,
                food.protein,
                food.fat,
                food.carbs,
                food.meal,
                food.addedDate,
                food.grams
            )
                foodRepository.addFood(updateFood)
        } else {
            foodRepository.addFood(food)
        }
    }

    fun deleteFood(food: Food) = viewModelScope.launch {
        foodRepository.deleteFood(food)
    }

}