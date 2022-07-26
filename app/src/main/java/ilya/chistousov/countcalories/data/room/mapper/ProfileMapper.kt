package ilya.chistousov.countcalories.data.room.mapper

import ilya.chistousov.countcalories.data.room.entity.ProfileEntity
import ilya.chistousov.countcalories.domain.model.Profile
import javax.inject.Inject


class ProfileMapper @Inject constructor(): BaseMapper<ProfileEntity, Profile> {

    override fun mapFromEntityToModel(entity: ProfileEntity): Profile {
        return Profile(
            entity.gender,
            entity.goal,
            entity.birthDate,
            entity.activityLevel,
            entity.currentGrowth,
            entity.currentWeight,
            entity.desiredWeight
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
            desiredWeight = model.desiredWeight
        )
    }

}