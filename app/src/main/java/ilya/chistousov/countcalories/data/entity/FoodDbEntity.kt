package ilya.chistousov.countcalories.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class FoodDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val calories: Double,
    val protein: Double,
    val fat: Double,
    val carbs: Double
)