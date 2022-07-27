package ilya.chistousov.countcalories.data.room.mapper

import ilya.chistousov.countcalories.data.room.entity.FoodEntity
import ilya.chistousov.countcalories.domain.model.Food
import javax.inject.Inject

class FoodEntityMapper @Inject constructor(): BaseMapper<FoodEntity, Food> {

    override fun mapFromEntityToModel(entity: FoodEntity): Food {
        return Food(
            entity.id,
            entity.name,
            entity.calories,
            entity.proteins,
            entity.fats,
            entity.carbs,
            entity.gram,
            entity.meal,
            entity.addedDate
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
            model.gram,
            model.meal,
            model.addedDate
        )
    }
}