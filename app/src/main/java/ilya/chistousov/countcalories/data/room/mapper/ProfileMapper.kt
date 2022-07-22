package ilya.chistousov.countcalories.data.room.mapper

import ilya.chistousov.countcalories.data.room.entity.ProfileDbEntity
import ilya.chistousov.countcalories.domain.model.Profile
import javax.inject.Inject


class ProfileMapper @Inject constructor(): BaseMapper<ProfileDbEntity, Profile> {

    override fun mapFromDbEntityToModel(dbEntity: ProfileDbEntity): Profile {
        return Profile(
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