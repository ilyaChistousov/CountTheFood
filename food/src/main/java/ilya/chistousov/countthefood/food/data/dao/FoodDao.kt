package ilya.chistousov.countthefood.food.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ilya.chistousov.countthefood.core.model.Meal
import ilya.chistousov.countthefood.food.data.entity.FoodEntity

@Dao
interface FoodDao {
    @Query("SELECT * FROM foods")
    fun getAllFood() : LiveData<List<FoodEntity>>

    @Query("SELECT * FROM foods WHERE id = :foodId")
    suspend fun getFoodById(foodId: Int) : FoodEntity?

    @Query("SELECT * FROM foods WHERE meal = :meal")
    fun getFoodByMeal(meal: Meal) : LiveData<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: FoodEntity)

    @Delete
    suspend fun deleteFood(food: FoodEntity)
}