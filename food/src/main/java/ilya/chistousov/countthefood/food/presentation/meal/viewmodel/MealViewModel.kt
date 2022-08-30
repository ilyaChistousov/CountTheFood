package ilya.chistousov.countthefood.food.presentation.meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.food.domain.repository.FoodRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MealViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    val foods = foodRepository.getAllFoods()

    fun addFood(food: Food) {
        viewModelScope.launch { foodRepository.addFood(food) }
    }

    fun deleteFood(food: Food) = viewModelScope.launch { foodRepository.deleteFood(food) }
}
