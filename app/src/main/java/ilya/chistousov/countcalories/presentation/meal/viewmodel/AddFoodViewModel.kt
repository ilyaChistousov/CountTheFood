package ilya.chistousov.countcalories.presentation.meal.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countcalories.domain.usecases.food.AddFoodUseCase
import javax.inject.Inject

class AddFoodViewModel @Inject constructor(private val addFoodUseCase: AddFoodUseCase) : ViewModel()