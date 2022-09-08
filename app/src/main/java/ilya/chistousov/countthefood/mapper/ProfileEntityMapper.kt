package ilya.chistousov.countthefood.mapper

import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.ActivityLevel
import ilya.chistousov.countthefood.core.model.Gender
import ilya.chistousov.countthefood.core.model.Goal
import ilya.chistousov.countthefood.core.model.Profile
import javax.inject.Inject

class ProfileEntityMapper @Inject constructor() : BaseMapper<ProfileEntity, Profile> {

    override fun mapFromEntityToModel(entity: ProfileEntity): Profile {
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

    override fun mapFromModelToEntity(model: Profile): ProfileEntity {
        return ProfileEntity(
            gender = model.gender.name,
            goal = model.goal.name,
            birthDate = model.birthDate,
            activityLevel = model.activityLevel.name,
            currentGrowth = model.currentGrowth,
            currentWeight = model.currentWeight,
            desiredWeight = model.desiredWeight,
            email = model.email
        )
    }
}