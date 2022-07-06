package ilya.chistousov.countcalories.data.entity.typeconvrerter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long) = Date(timestamp)

    @TypeConverter
    fun toLong(date: Date) = date.time
}