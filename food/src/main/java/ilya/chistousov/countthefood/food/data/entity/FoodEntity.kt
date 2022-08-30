package ilya.chistousov.countthefood.food.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ilya.chistousov.countthefood.core.model.Meal
import java.time.LocalDate

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carb: Float,
    val meal: Meal,
    val addedDate: LocalDate,
    val grams: Int
)