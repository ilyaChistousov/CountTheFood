package ilya.chistousov.countcalories.presentation.register.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ilya.chistousov.countcalories.data.database.AppDatabase
import ilya.chistousov.countcalories.data.repository.ProfileRepositoryImpl
import ilya.chistousov.countcalories.domain.model.ActivityLevel
import ilya.chistousov.countcalories.domain.model.Gender
import ilya.chistousov.countcalories.domain.model.Goal
import ilya.chistousov.countcalories.domain.usecases.profile.CreateProfileUseCase
import java.util.*

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val profileRepository = ProfileRepositoryImpl(database)
    private val createProfileUseCase = CreateProfileUseCase(profileRepository)

    private val _activityLevel = MutableLiveData<ActivityLevel>()
    val activityLevel: LiveData<ActivityLevel>
        get() = _activityLevel

    private val _birthDate = MutableLiveData<Date>()
    val birthDate: LiveData<Date>
        get() = _birthDate

    private val _currentGrowth = MutableLiveData<Int>()
    val currentGrowth: LiveData<Int>
        get() = _currentGrowth

    private val _currentWeight = MutableLiveData<Int>()
    val currentWeight: LiveData<Int>
        get() = _currentWeight

    private val _desiredWeight = MutableLiveData<Int>()
    val desiredWeight: LiveData<Int>
        get() = _desiredWeight

    private val _gender = MutableLiveData<Gender>()
    val gender: LiveData<Gender>
        get() = _gender

    private val _goal = MutableLiveData<Goal>()
    val goal: LiveData<Goal>
        get() = _goal

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

}