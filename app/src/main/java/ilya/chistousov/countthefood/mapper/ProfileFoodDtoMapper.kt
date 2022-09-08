package ilya.chistousov.countthefood.mapper

import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.api.dto.ProfileFoodDto
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import java.time.LocalDate
import javax.inject.Inject

class ProfileFoodDtoMapper @Inject constructor() : BaseMapper<ProfileFoodDto, Food>
{
    override fun  mapFromEntityToModel(entity: ProfileFoodDto): Food {
        return Food(
            name = entity.name,
            calories = entity.calories,
            protein = entity.protein,
            fat = entity.fat,
            carbs = entity.carbs,
            grams = entity.grams,
            meal = Meal.valueOf(entity.meal),
            addedDate = LocalDate.parse(entity.addedDate)
        )
    }

    override fun mapFromModelToEntity(model: Food): ProfileFoodDto {
        return ProfileFoodDto(
            model.name,
            model.calories,
            model.protein,
            model.fat,
            model.carbs,
            model.grams,
            model.addedDate.toString(),
            model.meal.name
        )
    }
}