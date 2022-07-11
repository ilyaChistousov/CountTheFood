package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDesiredWeightBinding
import ilya.chistousov.countcalories.domain.model.Goal
import ilya.chistousov.countcalories.domain.model.Goal.*
import ilya.chistousov.countcalories.presentation.register.viewmodel.ProfileViewModel

class DesiredWeightScreen
    : BaseScreen<FragmentDesiredWeightBinding>(
    FragmentDesiredWeightBinding::inflate
) {
    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[ProfileViewModel::class.java]
    }

    private var currentWeight = DEFAULT_WEIGHT
    private lateinit var currentGoal: Goal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goingToNextFragment()
        getDesiredWeight()
        getCurrentWeight()
        getCurrentGoal()
        showPreviousScreen()
    }

    private fun goingToNextFragment() {
        binding.buttonNextFragment.setOnClickListener {
            val weight = binding.textInputDesiredWeight.text?.toString() ?: ""
            if (weight == "") {
                Snackbar.make(requireView(), R.string.error_input_weight, Snackbar.LENGTH_SHORT)
                    .show()
            } else if (weight.toInt() < 20) {
                Snackbar.make(requireView(), getString(R.string.error_input_weight), Snackbar.LENGTH_SHORT)
                    .show()
            } else if (weight.toInt() > 300) {
                Snackbar.make(requireView(), R.string.error_input_weight, Snackbar.LENGTH_SHORT)
                    .show()
            } else if (currentGoal == WEIGHT_LOSS && currentWeight <= weight.toInt()) {
                Snackbar.make(requireView(), getString(R.string.error_input_weight_loss), Snackbar.LENGTH_SHORT)
                    .show()
            } else if (currentGoal == WEIGHT_GAIN && currentWeight >= weight.toInt()) {
                Snackbar.make(requireView(), getString(R.string.error_input_weight_gain), Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                profileViewModel.setDesiredWeight(weight.toInt())
                parentBinding.viewPager.currentItem = 7
            }
        }
    }

    private fun getCurrentWeight() {
        profileViewModel.currentWeight.observe(viewLifecycleOwner) {
            currentWeight = it
        }
    }

    private fun getCurrentGoal() {
        profileViewModel.goal.observe(viewLifecycleOwner) {
            currentGoal = it
        }
    }

    private fun getDesiredWeight() {
        profileViewModel.desiredWeight.observe(viewLifecycleOwner) {
            binding.textInputDesiredWeight.setText(it.toString())
        }
    }

    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            parentBinding.viewPager.currentItem = 5
        }
    }

    companion object {
        private const val DEFAULT_WEIGHT = 0
    }

}