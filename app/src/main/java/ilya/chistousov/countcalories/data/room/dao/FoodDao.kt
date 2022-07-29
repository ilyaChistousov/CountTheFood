package ilya.chistousov.countcalories.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ilya.chistousov.countcalories.data.room.entity.FoodEntity
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal

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

    @Query("SELECT * FROM foods WHERE isCustom = 1")
    fun getAllCustomFood() : LiveData<List<FoodEntity>>
}