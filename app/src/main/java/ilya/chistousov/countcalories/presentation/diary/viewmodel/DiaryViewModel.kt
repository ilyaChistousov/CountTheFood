package ilya.chistousov.countcalories.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countcalories.domain.usecases.food.GetAllFoodUseCase

class DiaryViewModel(getAllFoodUseCase: GetAllFoodUseCase) : ViewModel() {

    val foods = getAllFoodUseCase()
}
