package ilya.chistousov.countcalories.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ilya.chistousov.countcalories.domain.model.Meal
import java.time.LocalDate

@Entity(tableName = "foods")
data class FoodDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val calories: Int,
    val proteins: Float,
    val fats: Float,
    val carbs: Float,
    val meal: Meal,
    val addedDate: LocalDate
)