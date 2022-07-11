package ilya.chistousov.countcalories.presentation.foods.viewmodels

import android.app.Application
import androidx.lifecycle.*
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.repository.FoodRepositoryImpl
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.usecases.food.FoodUseCase
import kotlinx.coroutines.launch

class FoodViewModel(application: Application ) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val repository = FoodRepositoryImpl(database)
    private val foodUseCase = FoodUseCase(repository)

    private val _foods = foodUseCase.getAllFoodUseCase()
    val foods: LiveData<List<Food>> = _foods


    fun addFood(food: Food)  {
        viewModelScope.launch {foodUseCase.addFoodUseCase(food)}
    }

    fun deleteFood(food: Food) = viewModelScope.launch { foodUseCase.deleteFoodUseCase(food) }

    fun getFoodByMeal(meal: Meal) : LiveData<List<Food>> {
        return foodUseCase.getFoodByMealUseCase(meal)
    }

}