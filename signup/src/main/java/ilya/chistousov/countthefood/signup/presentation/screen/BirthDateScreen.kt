package ilya.chistousov.countthefood.signup.presentation.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import ilya.chistousov.countthefood.signup.R
import ilya.chistousov.countthefood.signup.databinding.FragmentBirthDateBinding
import ilya.chistousov.countthefood.signup.presentation.fragment.SignUpFragmentContainer.Companion.FIFTH_SCREEN
import ilya.chistousov.countthefood.signup.utils.BIRTH_DATE
import java.time.LocalDate
import java.time.Month
import java.time.Month.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.*

class BirthDateScreen :
    BaseScreen<FragmentBirthDateBinding>(
        FragmentBirthDateBinding::inflate
    ) {
    private val months = listOf(
        JANUARY, FEBRUARY, MARCH, APRIL,
        MAY, JUNE, JULY, AUGUST, SEPTEMBER,
        OCTOBER, NOVEMBER, DECEMBER
    )

    private var selectedBirthDate = LocalDate.now()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            getSelectedValue(savedInstanceState.getSerializable(BIRTH_DATE) as LocalDate)
            binding.buttonNextFragment.isEnabled = true
        }
        setNextScreen()
        setAutoComplete()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(BIRTH_DATE, selectedBirthDate)
    }

    private fun setNextScreen() {
        binding.buttonNextFragment.setOnClickListener {
            selectedBirthDate = validateDate()
            Log.d("BirthDate", "$selectedBirthDate")
            if (!binding.errorDate.isVisible) {
                createProfileViewModel.putString(BIRTH_DATE, getInputValueAsString())
                parentBinding.viewPager.currentItem = FIFTH_SCREEN
            }
        }
    }

    private fun validateDate(): LocalDate {
        var selectedDate = LocalDate.now()
        with(binding) {
            try {
                selectedDate = LocalDate.parse(
                    getInputValueAsString())
                errorDate.visibility = View.GONE

            } catch (ex: DateTimeParseException) {
                errorDate.visibility = View.VISIBLE
            } catch (ex: NoSuchElementException) {
                errorDate.visibility = View.VISIBLE
            } catch (ex : NumberFormatException) {
                errorDate.visibility = View.VISIBLE
            }
            return selectedDate
        }
    }

    private fun getInputValueAsString() : String {
        val selectedDay = binding.autoCompleteDay.text.toString()
        val selectedMonth = binding.autoCompleteMonth.text.toString()
        val selectedMonthValue = months
            .filter { it.getDisplayName(TextStyle.SHORT, Locale("ru")) == selectedMonth }
            .map { it.value }
            .first()
        val selectedYear = binding.autoCompleteYear.text.toString()
        val birthDate =
            LocalDate.of(selectedYear.toInt(), selectedMonthValue, selectedDay.toInt())
//        val monthInString = if (selectedMonthValue < 10) "0$selectedMonthValue" else "$selectedMonthValue"
//        val dayFormat = if (selectedDay.toInt() < 10) "0$selectedDay" else selectedDay

//        return "$selectedYear$monthInString$dayFormat"
        selectedBirthDate = birthDate
        return birthDate.toString()
    }

    private fun setAutoComplete() {
        val days = (1..31).toList()
        val localDate = LocalDate.now()
        val years = (1965..(localDate.year - 10)).toList()

        with(binding) {
            autoCompleteDay.setAdapter(ArrayAdapter(requireContext(), R.layout.auto_complete_item, days))
            setClickOnAutoComplete(autoCompleteDay)

            setClickOnAutoComplete(autoCompleteMonth, months)

            autoCompleteYear.setAdapter(ArrayAdapter(requireContext(), R.layout.auto_complete_item, years))
            setClickOnAutoComplete(autoCompleteYear)
        }
    }

    private fun setClickOnAutoComplete(autoComplete: AutoCompleteTextView, months: List<Month> = emptyList()) {
        autoComplete.isFocusable = false

        if (months.isNotEmpty()) {
            val map = months.map { it.getDisplayName(TextStyle.SHORT, Locale("ru")) }
            autoComplete.setAdapter(ArrayAdapter(requireContext(), R.layout.auto_complete_item, map))
        }

        autoComplete.setOnClickListener { autoComplete.showDropDown() }
    }

    private fun getSelectedValue(selectedBirthDate: LocalDate) {
        binding.autoCompleteDay.setText(selectedBirthDate.dayOfMonth.toString())
        binding.autoCompleteMonth.setText(selectedBirthDate.month.toString())
        binding.autoCompleteYear.setText(selectedBirthDate.year.toString())
    }
}