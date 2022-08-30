package ilya.chistousov.countthefood.food.presentation.meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.result.Result
import ilya.chistousov.countthefood.food.domain.repository.FoodRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val _foodFromApi = MutableLiveData<Result<List<Food>>>()
    val foodFromApi: LiveData<Result<List<Food>>> = _foodFromApi

    init {
        _foodFromApi.postValue(Result.Loading())
    }

    fun searchFoodByName(foodName: String) {
        viewModelScope.launch {
            foodRepository.searchFoodByName(foodName).observeForever {
                _foodFromApi.value = it
            }
        }
    }
}
