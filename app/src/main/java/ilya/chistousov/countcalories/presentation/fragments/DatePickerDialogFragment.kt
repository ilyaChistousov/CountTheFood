package ilya.chistousov.countcalories.presentation.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import java.text.SimpleDateFormat
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val calendar = Calendar.getInstance()
    private val args: DatePickerDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(requireActivity(), this, args.currentYear, args.currentMonth, args.currentDay)
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val selectedDate = getFormattedDate().format(calendar.time)

        setFragmentResult(REQUEST_KEY, bundleOf(EXTRA_DATE to selectedDate))
    }

    companion object {
        const val REQUEST_KEY = "Date request key"
        const val EXTRA_DATE = "Extra date"
        private const val DEFAULT_DATE_PATTERN = "EEE, d MMM, yyyy"

        fun getFormattedDate(pattern: String = DEFAULT_DATE_PATTERN) : SimpleDateFormat{
            return SimpleDateFormat(pattern, Locale("ru"))
        }
    }
}