package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentCurrentGrowthBinding
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModel

class CurrentGrowthScreen
    : BaseScreen<FragmentCurrentGrowthBinding>(
    FragmentCurrentGrowthBinding::inflate
) {

    private val createProfileViewModel: CreateProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[CreateProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goingToNextFragment()
        getCurrentGrowth()
        showPreviousScreen()
    }

    private fun goingToNextFragment() {
        binding.buttonNextFragment.setOnClickListener {
            val growth = binding.textInputCurrentGrowth.text?.toString() ?: ""
            if (growth == "") {
                Snackbar.make(requireView(), R.string.error_input_growth, Snackbar.LENGTH_SHORT).show()
            } else if (growth.toInt() < 100) {
                Snackbar.make(requireView(), R.string.error_input_growth, Snackbar.LENGTH_SHORT).show()
            } else if (growth.toInt() > 250) {
                Snackbar.make(requireView(), R.string.error_input_growth, Snackbar.LENGTH_SHORT).show()
            } else {
                createProfileViewModel.setCurrentGrowth(growth.toInt())
                parentBinding.viewPager.currentItem = 5
            }
        }
    }

    private fun getCurrentGrowth() {
        createProfileViewModel.currentGrowth.observe(viewLifecycleOwner) {
            binding.textInputCurrentGrowth.setText(it.toString())
        }
    }


    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            parentBinding.viewPager.currentItem = 3
        }
    }

}