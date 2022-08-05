package ilya.chistousov.countcalories.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ilya.chistousov.countcalories.domain.model.ActivityLevel
import ilya.chistousov.countcalories.domain.model.Gender
import ilya.chistousov.countcalories.domain.model.Goal
import java.time.LocalDate
import java.util.*

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
    val desiredWeight: Float
)