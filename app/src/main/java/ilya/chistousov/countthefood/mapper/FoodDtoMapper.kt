package ilya.chistousov.countthefood.mapper

import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import java.time.LocalDate
import javax.inject.Inject

class FoodDtoMapper @Inject constructor() : BaseMapper<FoodDto, Food> {

    override fun  mapFromEntityToModel(entity: FoodDto): Food {
        return Food(
            name = entity.name,
            calories = entity.calories,
            protein = entity.protein,
            fat = entity.fat,
            carbs = entity.carbs,
            grams = entity.grams,
            meal = Meal.NONE,
            addedDate = LocalDate.now()
        )
    }

    override fun mapFromModelToEntity(model: Food): FoodDto {
        return FoodDto(
            model.name,
            model.calories,
            model.protein,
            model.fat,
            model.carbs,
            model.grams
        )
    }

}