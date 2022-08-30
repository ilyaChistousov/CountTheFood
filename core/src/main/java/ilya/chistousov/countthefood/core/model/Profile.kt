package ilya.chistousov.countthefood.core.model

import java.time.LocalDate

data class Profile(
    val gender: Gender,
    val goal: Goal,
    val birthDate: LocalDate,
    val activityLevel: ActivityLevel,
    val currentGrowth: Int,
    val currentWeight: Float,
    val desiredWeight: Float,
    val email: String
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