package ilya.chistousov.countcalories.presentation.foods.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import java.time.LocalDate

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val args: DatePickerDialogFragmentArgs by navArgs()
    private val localDate = args.currentDate

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(
            requireActivity(),
            this,
            localDate.year,
            localDate.monthValue - 1,
            localDate.dayOfMonth
        )
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val localDate = LocalDate.now()
        val date = with(localDate) {
            withYear(year)
            withMonth(month)
            withDayOfMonth(dayOfMonth)
        }
        setFragmentResult(REQUEST_KEY, bundleOf(EXTRA_DATE to date))
    }

    companion object {
        const val REQUEST_KEY = "Date request key"
        const val EXTRA_DATE = "Extra date"
    }
}