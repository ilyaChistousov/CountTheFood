package ilya.chistousov.countcalories.domain.model

import java.util.*

data class Profile(
    val gender: Gender,
    val goal: Goal,
    val birthDate: Date,
    val activityLevel: ActivityLevel,
    val currentGrowth: Int,
    val currentWeight: Int,
    val desiredWeight: Int
    )

enum class Goal {
    WEIGHT_LOSS, KEEPING_CURRENT_WEIGHT, WEIGHT_GAIN
}

enum class Gender {
    FEMALE, MALE
}

enum class ActivityLevel {
    PASSIVE, INACTIVE, ACTIVE, HEAVILY_ACTIVE, EXTRA_ACTIVE
}