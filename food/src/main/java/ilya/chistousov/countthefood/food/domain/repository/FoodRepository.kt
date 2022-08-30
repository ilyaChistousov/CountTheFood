package ilya.chistousov.countthefood.food.domain.repository

import androidx.lifecycle.LiveData
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import ilya.chistousov.countthefood.core.result.Result

interface FoodRepository {

    suspend fun addFood(food: Food)

    suspend fun getFood(foodId: Int) : Food?

    fun getAllFoods() : LiveData<List<Food>>

    suspend fun deleteFood(food: Food)

    fun getFoodByMeal(meal: Meal) : LiveData<List<Food>>

    fun searchFoodByName(name: String) : LiveData<Result<List<Food>>>
}