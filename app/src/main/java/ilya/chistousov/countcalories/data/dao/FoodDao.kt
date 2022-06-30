package ilya.chistousov.countcalories.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ilya.chistousov.countcalories.data.entity.FoodDbEntity
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods")
    fun getAllFood() : LiveData<List<FoodDbEntity>>

    @Query("SELECT * FROM foods WHERE id = :foodId")
    fun getFoodById(foodId: Int) : LiveData<FoodDbEntity>

    @Query("SELECT * FROM foods WHERE meal = :meal")
    fun getFoodByMeal(meal: Meal) : LiveData<List<FoodDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: FoodDbEntity)

    @Delete
    suspend fun deleteFood(food: FoodDbEntity)

}