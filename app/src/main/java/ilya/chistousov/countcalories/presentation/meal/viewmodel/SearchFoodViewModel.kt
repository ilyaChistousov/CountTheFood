package ilya.chistousov.countcalories.presentation.meal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.usecases.foodApi.GetAllFoodFromApiByNameUseCase
import ilya.chistousov.countcalories.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFoodViewModel @Inject constructor(
    private val getFoodByNameFromApi: GetAllFoodFromApiByNameUseCase
) : ViewModel() {

    private val _foodFromApi = MutableLiveData<Resource<List<Food>>>()
    val foodFromApi: LiveData<Resource<List<Food>>> = _foodFromApi

    init {
        _foodFromApi.postValue(Resource.Loading(true))
    }

    fun searchFoodByName(foodName: String) {
        viewModelScope.launch {
            getFoodByNameFromApi(foodName).observeForever {
                Log.d("SearchedFood", "${it.data}")
                _foodFromApi.value = it
            }
        }
    }
}
