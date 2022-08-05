package ilya.chistousov.countcalories.presentation.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countcalories.domain.model.Goal
import ilya.chistousov.countcalories.domain.repository.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _selectedGoal = profileRepository.getGoal()
    val selectedGoal: LiveData<Goal> get() = _selectedGoal


    fun putString(key: String, value: String) = viewModelScope.launch {
        profileRepository.putString(key, value)
    }

    fun putInt(key: String, value: Int) = viewModelScope.launch {
        profileRepository.putInt(key, value)
    }

    fun putFloat(key: String, value: Float) = viewModelScope.launch {
        profileRepository.putFloat(key, value)
    }

    fun createProfile() = viewModelScope.launch { profileRepository.createProfile() }
}