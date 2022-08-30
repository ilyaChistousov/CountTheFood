package ilya.chistousov.countthefood.food.data.mapper

import ilya.chistousov.countthefood.core.entity.ProfileEntity
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() : BaseMapper<ProfileEntity, Profile> {

    override fun mapFromEntityToModel(entity: ProfileEntity): Profile {
        return Profile(
            entity.gender,
            entity.goal,
            entity.birthDate,
            entity.activityLevel,
            entity.currentGrowth,
            entity.currentWeight,
            entity.desiredWeight,
            entity.email
        )
    }

    override fun mapFromModelToEntity(model: Profile): ProfileEntity {
        return ProfileEntity(
            gender = model.gender,
            goal = model.goal,
            birthDate = model.birthDate,
            activityLevel = model.activityLevel,
            currentGrowth = model.currentGrowth,
            currentWeight = model.currentWeight,
            desiredWeight = model.desiredWeight,
            email = model.email
        )
    }
}