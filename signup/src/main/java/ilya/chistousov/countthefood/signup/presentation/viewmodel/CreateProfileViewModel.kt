package ilya.chistousov.countthefood.signup.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countthefood.core.model.Goal
import ilya.chistousov.countthefood.signup.domain.repository.CreateProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateProfileViewModel @Inject constructor(
    private val createProfileRepository: CreateProfileRepository,
) : ViewModel() {

    private val _selectedGoal = createProfileRepository.getGoal()
    val selectedGoal: LiveData<Goal> get() = _selectedGoal


    fun putString(key: String, value: String) = viewModelScope.launch {
        createProfileRepository.putString(key, value)
    }

    fun putInt(key: String, value: Int) = viewModelScope.launch {
        createProfileRepository.putInt(key, value)
    }

    fun putFloat(key: String, value: Float) = viewModelScope.launch {
        createProfileRepository.putFloat(key, value)
    }

    fun createProfile() = viewModelScope.launch { createProfileRepository.createProfile() }
}