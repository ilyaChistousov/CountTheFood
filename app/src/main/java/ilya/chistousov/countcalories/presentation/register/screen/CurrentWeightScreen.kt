package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentCurrentWeightBinding
import ilya.chistousov.countcalories.presentation.register.viewmodel.ProfileViewModel

class CurrentWeightScreen
    : BaseScreen<FragmentCurrentWeightBinding>(
    FragmentCurrentWeightBinding::inflate
) {
    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[ProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goingToNextFragment()
        getCurrentWeight()
        showPreviousScreen()
    }

    private fun goingToNextFragment() {
        binding.buttonNextFragment.setOnClickListener {
            val weight = binding.textInputCurrentWeight.text?.toString() ?: ""
            if (weight == "") {
                Snackbar.make(requireView(), R.string.error_input_weight, Snackbar.LENGTH_SHORT).show()
            } else if (weight.toInt() < 20) {
                Snackbar.make(requireView(), R.string.error_input_weight, Snackbar.LENGTH_SHORT).show()
            } else if (weight.toInt() > 300) {
                Snackbar.make(requireView(), R.string.error_input_weight, Snackbar.LENGTH_SHORT).show()
            } else {
                profileViewModel.setCurrentWeight(weight.toInt())
                parentBinding.viewPager.currentItem = 6
            }
        }
    }

    private fun getCurrentWeight() {
        profileViewModel.currentWeight.observe(viewLifecycleOwner) {
            binding.textInputCurrentWeight.setText(it.toString())
        }
    }

    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            parentBinding.viewPager.currentItem = 4
        }
    }

}