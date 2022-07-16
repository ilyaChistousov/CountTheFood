package ilya.chistousov.countcalories.presentation.util

import android.widget.DatePicker
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*

fun DatePicker.getDate() : Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    return calendar.time
}

fun Date.getYearFromDate() : Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return Period.between(
        LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)),
        LocalDate.now()
    ).years
}

fun List<Food>.filterListFoodByMealAndDate(meal: Meal, localDate: LocalDate): List<Food> {
    return filter { it.meal == meal }
        .filter { it.addedDate == localDate }
}


fun List<Food>.filterListFoodByDate(currentDate: LocalDate): List<Food> {
    return filter { it.addedDate == currentDate }
}

fun LocalDate.convertToDate() : Date {
    return Date.from(atStartOfDay(ZoneId.systemDefault()).toInstant())
}

fun Calendar.convertToLocalDate() : LocalDate {
    return time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
}