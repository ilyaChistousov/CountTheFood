package ilya.chistousov.countcalories.data.mapper

import ilya.chistousov.countcalories.data.entity.FoodDbEntity
import ilya.chistousov.countcalories.domain.model.Food

class FoodMapper : BaseMapper<FoodDbEntity, Food> {

    override fun mapFromDbEntityToModel(dbEntity: FoodDbEntity): Food {
        return Food(
            dbEntity.id,
            dbEntity.name,
            dbEntity.calories,
            dbEntity.protein,
            dbEntity.fat,
            dbEntity.carbs
        )
    }

    override fun mapFromModelToDbEntity(model: Food): FoodDbEntity {
        return FoodDbEntity(
            model.id,
            model.name,
            model.calories,
            model.protein,
            model.fat,
            model.carbs
        )
    }
}