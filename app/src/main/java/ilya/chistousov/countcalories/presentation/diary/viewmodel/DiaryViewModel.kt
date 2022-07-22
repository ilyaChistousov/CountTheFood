package ilya.chistousov.countcalories.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ilya.chistousov.countcalories.domain.usecases.food.GetAllFoodUseCase

class DiaryViewModel(getAllFoodUseCase: GetAllFoodUseCase) : ViewModel() {

    val foods = getAllFoodUseCase()
}

class DiaryViewModelFactory @AssistedInject constructor(
    private val getAllFoodUseCase: GetAllFoodUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DiaryViewModel(getAllFoodUseCase) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(): DiaryViewModelFactory
    }
}