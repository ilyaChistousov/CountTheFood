package ilya.chistousov.countthefood.mapper

import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.ActivityLevel
import ilya.chistousov.countthefood.core.model.Gender
import ilya.chistousov.countthefood.core.model.Goal
import ilya.chistousov.countthefood.core.model.Profile
import javax.inject.Inject

class ProfileDtoMapper @Inject constructor() : BaseMapper<ProfileDto, Profile> {

    override fun mapFromEntityToModel(entity: ProfileDto): Profile {
        return Profile(
            Gender.valueOf(entity.gender),
            Goal.valueOf(entity.goal),
            entity.birthDate,
            ActivityLevel.valueOf(entity.activityLevel),
            entity.currentGrowth,
            entity.currentWeight,
            entity.desiredWeight,
            entity.email
        )
    }

    override fun mapFromModelToEntity(model: Profile): ProfileDto {
        return ProfileDto(
            model.gender.name,
            model.goal.name,
            model.birthDate,
            model.activityLevel.name,
            model.currentGrowth,
            model.currentWeight,
            model.desiredWeight,
            model.email
        )
    }
}