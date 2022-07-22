package ilya.chistousov.countcalories.presentation.foods.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.usecases.food.AddFoodUseCase
import ilya.chistousov.countcalories.domain.usecases.food.DeleteFoodUseCase
import ilya.chistousov.countcalories.domain.usecases.food.GetAllFoodUseCase
import kotlinx.coroutines.launch

class MealViewModel(
    getAllFoodUseCase: GetAllFoodUseCase,
    private val addFoodUseCase: AddFoodUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase
) : ViewModel() {

    val foods = getAllFoodUseCase()

    fun addFood(food: Food) {
        viewModelScope.launch { addFoodUseCase(food) }
    }

    fun deleteFood(food: Food) = viewModelScope.launch { deleteFoodUseCase(food) }
}

class MealViewModelFactory @AssistedInject constructor(
    private val getAllFoodUseCase: GetAllFoodUseCase,
    private val addFoodUseCase: AddFoodUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(getAllFoodUseCase, addFoodUseCase, deleteFoodUseCase) as T
    }

    @AssistedFactory
    interface Factory {
        fun create() : MealViewModelFactory
    }
}