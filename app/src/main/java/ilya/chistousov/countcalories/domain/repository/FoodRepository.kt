package ilya.chistousov.countcalories.domain.repository

import ilya.chistousov.countcalories.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    suspend fun addFood(food: Food)

    fun getFood(foodId: Int) : Flow<Food>

    fun getAllFoods() : Flow<List<Food>>

    suspend fun deleteFood(food: Food)
}