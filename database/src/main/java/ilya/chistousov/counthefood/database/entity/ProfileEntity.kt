package ilya.chistousov.counthefood.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gender: String,
    val goal: String,
    val birthDate: String,
    val activityLevel: String,
    val currentGrowth: Int,
    val currentWeight: Float,
    val desiredWeight: Float,
    val email: String
)