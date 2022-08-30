package ilya.chistousov.countthefood.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ilya.chistousov.countthefood.core.model.ActivityLevel
import ilya.chistousov.countthefood.core.model.Gender
import ilya.chistousov.countthefood.core.model.Goal
import java.time.LocalDate

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gender: Gender,
    val goal: Goal,
    val birthDate: LocalDate,
    val activityLevel: ActivityLevel,
    val currentGrowth: Int,
    val currentWeight: Float,
    val desiredWeight: Float,
    val email: String
)