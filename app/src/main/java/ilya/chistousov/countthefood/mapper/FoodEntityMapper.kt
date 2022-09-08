package ilya.chistousov.countthefood.mapper

import ilya.chistousov.counthefood.database.entity.FoodEntity
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.model.Meal
import javax.inject.Inject

class FoodEntityMapper @Inject constructor() : BaseMapper<FoodEntity, Food> {

    override fun  mapFromEntityToModel(entity: FoodEntity): Food {
        return Food(
            entity.id,
            entity.name,
            entity.calories,
            entity.protein,
            entity.fat,
            entity.carbs,
            entity.grams,
            Meal.valueOf(entity.meal),
            entity.addedDate
        )
    }

    override fun mapFromModelToEntity(model: Food): FoodEntity {
        return FoodEntity(
            name = model.name,
            calories = model.calories,
            protein = model.protein,
            fat = model.fat,
            carbs = model.carbs,
            grams = model.grams,
            meal = model.meal.name,
            addedDate = model.addedDate
        )
    }
}