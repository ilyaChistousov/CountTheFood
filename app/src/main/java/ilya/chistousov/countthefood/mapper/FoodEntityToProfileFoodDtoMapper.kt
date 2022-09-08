package ilya.chistousov.countthefood.mapper

import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Meal
import java.time.LocalDate
import javax.inject.Inject

class FoodEntityToProfileFoodDtoMapper @Inject constructor() : BaseMapper<FoodEntity, ProfileFoodDto> {

    override fun mapFromEntityToModel(entity: FoodEntity): ProfileFoodDto {
        return ProfileFoodDto(
            entity.name,
            entity.calories,
            entity.protein,
            entity.fat,
            entity.carbs,
            entity.grams,
            entity.addedDate.toString(),
            entity.meal
        )
    }

    override fun mapFromModelToEntity(model: ProfileFoodDto): FoodEntity {
        return FoodEntity(
            name = model.name,
            calories = model.calories,
            protein = model.protein,
            fat = model.fat,
            carbs = model.carbs,
            grams = model.grams,
            addedDate = LocalDate.parse(model.addedDate),
            meal = model.meal
        )
    }

}