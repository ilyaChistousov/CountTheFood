package ilya.chistousov.countcalories.presentation.register.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.room.repository.ProfileRepositoryImpl
import ilya.chistousov.countcalories.domain.model.ActivityLevel
import ilya.chistousov.countcalories.domain.model.Gender
import ilya.chistousov.countcalories.domain.model.Goal
import ilya.chistousov.countcalories.domain.model.Profile
import ilya.chistousov.countcalories.domain.usecases.profile.CreateProfileUseCase
import kotlinx.coroutines.launch
import java.util.*

class CreateProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val profileRepository = ProfileRepositoryImpl(database)
    private val createProfileUseCase = CreateProfileUseCase(profileRepository)

    private val _activityLevel = MutableLiveData<ActivityLevel>()
    val activityLevel: LiveData<ActivityLevel> = _activityLevel

    private val _birthDate = MutableLiveData<Date>()
    val birthDate: LiveData<Date> = _birthDate

    private val _currentGrowth = MutableLiveData<Int>()
    val currentGrowth: LiveData<Int> = _currentGrowth

    private val _currentWeight = MutableLiveData<Int>()
    val currentWeight: LiveData<Int> = _currentWeight

    private val _desiredWeight = MutableLiveData<Int>()
    val desiredWeight: LiveData<Int> = _desiredWeight

    private val _gender = MutableLiveData<Gender>()
    val gender: LiveData<Gender> = _gender

    private val _goal = MutableLiveData<Goal>()
    val goal: LiveData<Goal> = _goal

    fun setGoal(goal: Goal) {
        this._goal.value = goal
    }

    fun setActivityLevel(activityLevel: ActivityLevel) {
        this._activityLevel.value = activityLevel
    }

    fun setBirthDate(birthDate: Date) {
        this._birthDate.value = birthDate
    }

    fun setCurrentGrowth(currentGrowth: Int) {
        this._currentGrowth.value = currentGrowth
    }

    fun setCurrentWeight(currentWeight: Int) {
        this._currentWeight.value = currentWeight
    }

    fun setDesiredWeight(desiredWeight: Int) {
        this._desiredWeight.value = desiredWeight
    }

    fun setGender(gender: Gender) {
        this._gender.value = gender
    }

    fun createProfile() {
        val profile = Profile(
            _gender.value!!,
            _goal.value!!,
            _birthDate.value!!,
            _activityLevel.value!!,
            _currentGrowth.value!!,
            _currentWeight.value!!,
            _desiredWeight.value!!
        )
        viewModelScope.launch { createProfileUseCase(profile) }
    }


}