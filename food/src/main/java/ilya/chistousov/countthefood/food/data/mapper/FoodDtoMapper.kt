package ilya.chistousov.countthefood.food.data.mapper

import ilya.chistousov.countthefood.api.dto.FoodDto
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.api.result.ResponseMapper
import java.time.LocalDate
import javax.inject.Inject

class FoodDtoListMapper @Inject constructor() : ResponseMapper<List<FoodDto>, List<Food>> {

    override fun map(response: List<FoodDto>): List<Food> {
        return response.map {
            Food(
                name = it.name,
                calories = it.calories,
                protein = it.protein,
                fat = it.fat,
                carbs = it.carbs,
                addedDate = LocalDate.now(),
                grams = it.grams
            )
        }
    }
}