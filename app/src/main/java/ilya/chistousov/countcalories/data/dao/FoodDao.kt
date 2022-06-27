package ilya.chistousov.countcalories.data.dao

import androidx.room.*
import ilya.chistousov.countcalories.data.entity.FoodDbEntity
import ilya.chistousov.countcalories.domain.model.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods")
    fun getAllFood() : Flow<List<FoodDbEntity>>

    @Query("SELECT * FROM foods WHERE id = :foodId")
    fun getFoodById(foodId: Int) : Flow<FoodDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFood(food: FoodDbEntity)

    @Delete
    fun deleteFood(food: FoodDbEntity)
}