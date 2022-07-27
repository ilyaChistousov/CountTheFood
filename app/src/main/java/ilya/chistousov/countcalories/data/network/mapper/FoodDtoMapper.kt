package ilya.chistousov.countcalories.data.network.mapper

import ilya.chistousov.countcalories.data.network.dto.FoodDto
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.util.Mapper
import java.time.LocalDate
import javax.inject.Inject

class FoodDtoMapper @Inject constructor() : Mapper<FoodDto, Food> {

    override fun map(target: FoodDto): Food {
        return Food(
            name = target.name,
            calories = target.calories.toInt(),
            protein = target.protein,
            fat = target.fat,
            carbs = target.carbohydrate,
            addedDate = LocalDate.now()
        )
    }
}

class FoodDtoListMapper @Inject constructor() : Mapper<List<FoodDto>, List<Food>> {
    override fun map(target: List<FoodDto>): List<Food> {
        return target.map { Food(
            name = it.name,
            calories = it.calories.toInt(),
            protein = it.protein,
            fat = it.fat,
            carbs = it.carbohydrate,
            addedDate = LocalDate.now())
        }
    }
}