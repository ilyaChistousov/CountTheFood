package ilya.chistousov.countthefood.data.room.typeconvrerter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_DATE

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = formatter.parse(value, LocalDate::from)

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate): String = localDate.format(formatter)
}
