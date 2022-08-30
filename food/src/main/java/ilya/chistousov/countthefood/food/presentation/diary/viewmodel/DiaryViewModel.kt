package ilya.chistousov.countthefood.food.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countthefood.food.domain.repository.FoodRepository
import javax.inject.Inject

class DiaryViewModel @Inject constructor(foodRepository: FoodRepository) : ViewModel() {

    val foods = foodRepository.getAllFoods()
}
