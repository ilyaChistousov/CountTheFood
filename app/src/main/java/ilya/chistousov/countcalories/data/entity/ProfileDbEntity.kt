package ilya.chistousov.countcalories.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ilya.chistousov.countcalories.domain.model.ActivityLevel
import ilya.chistousov.countcalories.domain.model.Gender
import ilya.chistousov.countcalories.domain.model.Goal
import java.util.*

@Entity(tableName = "profile")
data class ProfileDbEntity(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val gender: Gender,
    val goal: Goal,
    val birthDate: Date,
    val activityLevel: ActivityLevel,
    val currentGrowth: Int,
    val currentWeight: Int,
    val desiredWeight: Int
)