package ilya.chistousov.countcalories.presentation.diary.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import ilya.chistousov.countcalories.util.LEFT_ANIM
import ilya.chistousov.countcalories.util.RIGHT_ANIM
import java.time.LocalDate

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val args: DatePickerDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val localDate = args.currentDate
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
        val slideAnim = if (date.isAfter(args.currentDate)) {
            RIGHT_ANIM
        } else {
            LEFT_ANIM
        }
        setFragmentResult(REQUEST_DATE_KEY, bundleOf(EXTRA_DATE to date))
        setFragmentResult(REQUEST_ANIM_KEY, bundleOf(EXTRA_ANIM to slideAnim))
    }

    companion object {
        const val REQUEST_DATE_KEY = "Date request key"
        const val EXTRA_DATE = "Extra date"
        const val REQUEST_ANIM_KEY = "Anim request key"
        const val EXTRA_ANIM = "Extra anim"
    }
}