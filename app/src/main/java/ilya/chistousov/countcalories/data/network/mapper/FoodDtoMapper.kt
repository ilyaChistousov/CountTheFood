package ilya.chistousov.countcalories.data.network.mapper

import ilya.chistousov.countcalories.data.network.dto.FoodDto
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.util.Mapper
import java.time.LocalDate
import javax.inject.Inject

class FoodDtoMapper @Inject constructor() : Mapper<FoodDto, Food> {

    override fun map(target: FoodDto): Food {
        return Food(
            target.id,
            target.name,
            target.calories.toInt(),
            target.protein,
            target.fat,
            target.carbohydrate,
            addedDate = LocalDate.now()
        )
    }
}

class FoodDtoListMapper @Inject constructor() : Mapper<List<FoodDto>, List<Food>> {
    override fun map(target: List<FoodDto>): List<Food> {
        return target.map { Food(
            it.id,
            it.name,
            it.calories.toInt(),
            it.protein,
            it.fat,
            it.carbohydrate,
            addedDate = LocalDate.now())
        }
    }
}