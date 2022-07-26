package ilya.chistousov.countcalories.domain.usecases.foodApi

import androidx.lifecycle.LiveData
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.repository.FoodApiRepository
import ilya.chistousov.countcalories.util.Resource
import javax.inject.Inject

class GetFoodFromApiByIdUseCase @Inject constructor(private val repository: FoodApiRepository) {

    suspend operator fun invoke(id: Int) : LiveData<Resource<Food>> {
        return repository.getFoodById(id)
    }
}