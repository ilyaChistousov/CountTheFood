package ilya.chistousov.counthefood.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ilya.chistousov.counthefood.database.entity.FoodEntity

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods")
    fun getAllFood() : LiveData<List<FoodEntity>>

    @Query("SELECT * FROM foods WHERE id = :foodId")
    suspend fun getFoodById(foodId: Int) : FoodEntity?

    @Query("SELECT * FROM foods WHERE meal = :meal")
    fun getFoodByMeal(meal: String) : LiveData<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: FoodEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListFood(foods: List<FoodEntity>)

    @Delete
    suspend fun deleteFood(food: FoodEntity)
}