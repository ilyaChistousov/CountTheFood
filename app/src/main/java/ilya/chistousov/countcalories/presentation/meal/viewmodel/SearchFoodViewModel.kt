package ilya.chistousov.countcalories.presentation.meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.usecases.foodApi.GetAllFoodFromApiByNameUseCase
import ilya.chistousov.countcalories.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFoodViewModel @Inject constructor(
    private val getFoodByNameFromApiUseCase: GetAllFoodFromApiByNameUseCase
) : ViewModel() {

    private val _foodFromApi = MutableLiveData<Response<List<Food>>>()
    val foodFromApi: LiveData<Response<List<Food>>> = _foodFromApi

    init {
        _foodFromApi.postValue(Response.Loading(true))
    }

    fun searchFoodByName(foodName: String) {
        viewModelScope.launch {
            getFoodByNameFromApiUseCase(foodName).observeForever {
                _foodFromApi.value = it
            }
        }
    }
}
