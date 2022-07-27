package ilya.chistousov.countcalories.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countcalories.domain.usecases.food.GetAllFoodUseCase
import javax.inject.Inject

class DiaryViewModel @Inject constructor(getAllFoodUseCase: GetAllFoodUseCase) : ViewModel() {

    val foods = getAllFoodUseCase()
}
