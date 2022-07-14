package ilya.chistousov.countcalories.presentation.foods.viewmodels

import android.app.Application
import androidx.lifecycle.*
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.room.repository.FoodRepositoryImpl
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.usecases.food.FoodUseCase
import kotlinx.coroutines.launch

class FoodViewModel(application: Application ) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val repository = FoodRepositoryImpl(database)
    private val foodUseCase = FoodUseCase(repository)

    val foods = foodUseCase.getAllFoodUseCase()

    fun addFood(food: Food)  {
        viewModelScope.launch {foodUseCase.addFoodUseCase(food)}
    }

    fun deleteFood(food: Food) = viewModelScope.launch { foodUseCase.deleteFoodUseCase(food) }

}