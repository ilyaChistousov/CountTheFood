package ilya.chistousov.counthefood.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbs: Float,
    val grams: Int,
    val meal: String,
    val addedDate: LocalDate,
)