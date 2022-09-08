package ilya.chistousov.countthefood.signin.data.mapper

import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.api.result.ResponseMapper
import ilya.chistousov.countthefood.core.model.ActivityLevel
import ilya.chistousov.countthefood.core.model.Gender
import ilya.chistousov.countthefood.core.model.Goal
import ilya.chistousov.countthefood.core.model.Profile
import javax.inject.Inject

class ProfileResponseMapper @Inject constructor() : ResponseMapper<ProfileDto, Profile> {

    override fun map(response: ProfileDto): Profile {
        return Profile(
            Gender.valueOf(response.gender),
            Goal.valueOf(response.goal),
            response.birthDate,
            ActivityLevel.valueOf(response.activityLevel),
            response.currentGrowth,
            response.currentWeight,
            response.desiredWeight,
            response.email
        )
    }
}