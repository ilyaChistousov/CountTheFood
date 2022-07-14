package ilya.chistousov.countcalories.data.entity

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
    val proteins: Double,
    val fats: Double,
    val carbs: Double,
    val meal: Meal,
    val addedDate: LocalDate
)