package ilya.chistousov.countcalories.data.mapper

import ilya.chistousov.countcalories.data.entity.ProfileDbEntity
import ilya.chistousov.countcalories.domain.model.Profile


class ProfileMapper : BaseMapper<ProfileDbEntity, Profile> {

    override fun mapFromDbEntityToModel(dbEntity: ProfileDbEntity): Profile {
        return Profile(
            dbEntity.email,
            dbEntity.gender,
            dbEntity.goal,
            dbEntity.birthDate,
            dbEntity.activityLevel,
            dbEntity.currentGrowth,
            dbEntity.currentWeight,
            dbEntity.desiredWeight
        )
    }

    override fun mapFromModelToDbEntity(model: Profile): ProfileDbEntity {
        return ProfileDbEntity(
            model.email,
            model.gender,
            model.goal,
            model.birthDate,
            model.activityLevel,
            model.currentGrowth,
            model.currentWeight,
            model.desiredWeight
        )
    }

}