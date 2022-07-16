package ilya.chistousov.countcalories.presentation.register.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentBirthDateBinding
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModel
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModelFactory
import ilya.chistousov.countcalories.presentation.util.getDate
import java.util.*
import javax.inject.Inject

class BirthDateScreen :
    BaseScreen<FragmentBirthDateBinding>(
        FragmentBirthDateBinding::inflate
    ) {

    private val createProfileViewModel: CreateProfileViewModel by viewModels {
        createProfileFactory.create()
    }

    @Inject
    lateinit var createProfileFactory: CreateProfileViewModelFactory.Factory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setDatePicker()
        goingToNextFragment()
        getSelectedDate()
        showPreviousScreen()
    }

    private fun goingToNextFragment() {
        binding.buttonNextFragment.setOnClickListener {
            createProfileViewModel.setBirthDate(binding.datePicker.getDate())
            parentBinding.viewPager.currentItem = 4
        }
    }

    private fun setDatePicker() {
        binding.datePicker.maxDate = Date().time
        binding.datePicker.minDate = getMinDate()
    }


    private fun getMinDate(): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -80)
        return calendar.timeInMillis
    }

    private fun getSelectedDate() {
        createProfileViewModel.birthDate.observe(viewLifecycleOwner) {
            if (it != null) {
                val calendar = Calendar.getInstance()
                calendar.time = it
                binding.datePicker.updateDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
            }
        }
    }

    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            parentBinding.viewPager.currentItem = 2
        }
    }
}