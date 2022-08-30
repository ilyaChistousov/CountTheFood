package ilya.chistousov.countthefood.signup.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import ilya.chistousov.countthefood.core.model.Goal
import ilya.chistousov.countthefood.signup.R
import ilya.chistousov.countthefood.signup.databinding.FragmentProfileInfoScreenBinding
import ilya.chistousov.countthefood.signup.presentation.fragment.SignUpFragmentContainer.Companion.SIXTH_SCREEN
import ilya.chistousov.countthefood.signup.utils.CURRENT_GROWTH
import ilya.chistousov.countthefood.signup.utils.CURRENT_WEIGHT
import ilya.chistousov.countthefood.signup.utils.DESIRED_WEIGHT

class ProfileInfoScreen : BaseScreen<FragmentProfileInfoScreenBinding>(
    FragmentProfileInfoScreenBinding::inflate
) {

    private var selectedGrowth: Int = 0
    private var selectedCurrentWeight: Float = 0F
    private var selectedDesiredWeight: Float = 0F
    private var currentGoal: Goal? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            binding.inputGrowth.setText(selectedGrowth.toString())
            binding.inputCurrentWeight.setText(selectedCurrentWeight.toString())
            binding.inputDesiredWeight.setText(selectedDesiredWeight.toString())
            binding.buttonNextFragment.isEnabled = true
        }
        getCurrentGoal()
        onTextChangeTextInput()
        disableDesiredWeightFields()
        setNextScreen()
    }

    private fun getCurrentGoal() {
        createProfileViewModel.selectedGoal.observe(viewLifecycleOwner) {
            currentGoal = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_GROWTH, selectedGrowth)
        outState.putFloat(CURRENT_WEIGHT, selectedCurrentWeight)
        outState.putFloat(DESIRED_WEIGHT, selectedDesiredWeight)
    }

    private fun onTextChangeTextInput() {
        binding.inputGrowth.doOnTextChanged { _, _, _, _ ->
            validateInput()
        }
        binding.inputDesiredWeight.doOnTextChanged { _, _, _, _ ->
            validateInput()
        }
        binding.inputCurrentWeight.doOnTextChanged { _, _, _, _ ->
            validateInput()
        }
    }

    private fun validateInput() {
        with(binding) {
            val inputGrowth = inputGrowth.text.toString()
            val growth = if (inputGrowth.isNotEmpty()) {
                inputGrowth.toInt()
            } else {
                0
            }

            val inputCurrentWeight = inputCurrentWeight.text.toString()
            val currentWeight = if (inputCurrentWeight.isNotEmpty()) {
                inputCurrentWeight.toFloat()
            } else {
                0F
            }

            val inputDesiredWeight = inputDesiredWeight.text.toString()
            val desiredWeight = if (inputDesiredWeight.isNotEmpty()) {
                inputDesiredWeight.toFloat()
            } else {
                0F
            }

            if (currentGoal == Goal.KEEPING_CURRENT_WEIGHT) {
                buttonNextFragment.isEnabled = validateGrowth(growth) && validateCurrentWeight(currentWeight)
            } else {
                buttonNextFragment.isEnabled = validateGrowth(growth) && validateCurrentWeight(currentWeight) &&
                        validateDesiredWeight(desiredWeight, currentWeight, currentGoal)
            }
        }
    }

    private fun validateGrowth(growth: Int): Boolean {
        if (growth < MIN_GROWTH || growth > MAX_GROWTH) {
            binding.layoutGrowth.error = getString(R.string.error_input_growth)
            return false
        }
        binding.layoutGrowth.error = null
        return true
    }

    private fun validateCurrentWeight(currentWeight: Float): Boolean {
        if (currentWeight < MIN_WEIGHT || currentWeight > MAX_WEIGHT) {
            binding.layoutCurrentWeight.error = getString(R.string.error_input_weight)
            return false
        }
        binding.layoutCurrentWeight.error = null
        return true
    }

    private fun validateDesiredWeight(desiredWeight: Float, currentWeight: Float, currentGoal: Goal?): Boolean {
        if (currentGoal == Goal.WEIGHT_LOSS && (desiredWeight >= currentWeight || desiredWeight < MIN_WEIGHT)) {
            binding.layoutDesiredWeight.error = getString(R.string.error_input_weight)
            return false
        }
        if (currentGoal == Goal.WEIGHT_GAIN && (desiredWeight <= currentWeight || desiredWeight > MAX_WEIGHT)) {
            binding.layoutDesiredWeight.error = getString(R.string.error_input_weight)
            return false
        }
        binding.layoutDesiredWeight.error = null
        return true
    }


    private fun setNextScreen() {
        with(binding) {
            buttonNextFragment.setOnClickListener {
                selectedGrowth = inputGrowth.text.toString().toInt()
                selectedCurrentWeight = inputCurrentWeight.text.toString().toFloat()
                selectedDesiredWeight = if (currentGoal == Goal.KEEPING_CURRENT_WEIGHT) {
                    0F
                } else {
                    inputDesiredWeight.text.toString().toFloat()
                }

                createProfileViewModel.putInt(CURRENT_GROWTH, selectedGrowth)
                createProfileViewModel.putFloat(CURRENT_WEIGHT, selectedCurrentWeight)
                createProfileViewModel.putFloat(DESIRED_WEIGHT, selectedDesiredWeight)
                parentBinding.viewPager.currentItem = SIXTH_SCREEN
            }
        }
    }

    private fun disableDesiredWeightFields() {
        createProfileViewModel.selectedGoal.observe(viewLifecycleOwner) {
            if (it == Goal.KEEPING_CURRENT_WEIGHT) {
                binding.layoutDesiredWeight.visibility = View.GONE
                binding.textDesiredWeight.visibility = View.GONE
                binding.inputDesiredWeight.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val MIN_GROWTH = 120
        private const val MAX_GROWTH = 250
        private const val MIN_WEIGHT = 30
        private const val MAX_WEIGHT = 150
    }
}