package ilya.chistousov.countthefood.food.data.mapper

import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.food.data.entity.FoodEntity
import javax.inject.Inject

class FoodEntityMapper @Inject constructor(): BaseMapper<FoodEntity, Food> {

    override fun mapFromEntityToModel(entity: FoodEntity): Food {
        return Food(
            entity.id,
            entity.name,
            entity.calories,
            entity.protein,
            entity.fat,
            entity.carb,
            entity.meal,
            entity.addedDate,
            entity.grams
        )
    }

    override fun mapFromModelToEntity(model: Food): FoodEntity {
        return FoodEntity(
            model.id,
            model.name,
            model.calories,
            model.protein,
            model.fat,
            model.carbs,
            model.meal,
            model.addedDate,
            model.grams
        )
    }
}