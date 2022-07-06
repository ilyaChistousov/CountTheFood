package ilya.chistousov.countcalories.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DateViewModel : ViewModel() {

    private val calendar =  Calendar.getInstance()

    private val _currentDate = MutableLiveData(calendar.time)
    val currentDate: LiveData<Date>
        get() = _currentDate

    fun selectNextDay() {
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        _currentDate.value = calendar.time
    }

    fun selectPreviousDay() {
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        _currentDate.value = calendar.time
    }

    fun setDate(date: Date)  {
        calendar.time = date
        _currentDate.value = date
    }
}