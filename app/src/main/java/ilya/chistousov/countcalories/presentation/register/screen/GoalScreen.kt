package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.View
import com.google.android.material.card.MaterialCardView
import ilya.chistousov.countcalories.databinding.FragmentGoalBinding
import ilya.chistousov.countcalories.domain.model.Goal
import ilya.chistousov.countcalories.domain.model.Goal.*
import ilya.chistousov.countcalories.presentation.register.fragment.RegisterFragmentContainer.Companion.SECOND_SCREEN
import ilya.chistousov.countcalories.util.GOAL

class GoalScreen
    : BaseScreen<FragmentGoalBinding>(
    FragmentGoalBinding::inflate
) {

    private var selectedGoal: Goal? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restoreDate()
        setNextScreen()
        selectWeightLoss()
        selectKeepingCurrentWeight()
        selectWeightGain()
    }

    private fun restoreDate() {
        if (selectedGoal != null) {
            getSelectedGoal(selectedGoal!!)
            binding.buttonNextFragment.isEnabled = true
        }
    }

    private fun setNextScreen() {
        binding.buttonNextFragment.setOnClickListener {
            createProfileViewModel.putString(GOAL, selectedGoal!!.name)
            parentBinding.viewPager.currentItem = SECOND_SCREEN
        }
    }

    private fun selectWeightLoss() {
        binding.cardViewWeightLoss.setOnClickListener {
            setEnable(binding.cardViewWeightLoss)
            selectedGoal = WEIGHT_LOSS
        }
    }

    private fun selectKeepingCurrentWeight() {
        binding.cardViewKeepingCurrentWeight.setOnClickListener {
            setEnable(binding.cardViewKeepingCurrentWeight)
            selectedGoal = KEEPING_CURRENT_WEIGHT
        }
    }

    private fun selectWeightGain() {
        binding.cardViewWeightGain.setOnClickListener {
            setEnable(binding.cardViewWeightGain)
            selectedGoal = WEIGHT_GAIN
        }
    }

    private fun setEnable(materialCard: MaterialCardView) {
        binding.cardViewWeightGain.isChecked = false
        binding.cardViewWeightLoss.isChecked = false
        binding.cardViewKeepingCurrentWeight.isChecked = false
        materialCard.isChecked = true
        binding.buttonNextFragment.isEnabled = true
    }

    private fun getSelectedGoal(selectedGoal: Goal) {
        when (selectedGoal) {
            WEIGHT_LOSS -> enableCardView(binding.cardViewWeightLoss)
            KEEPING_CURRENT_WEIGHT -> enableCardView(binding.cardViewKeepingCurrentWeight)
            WEIGHT_GAIN -> enableCardView(binding.cardViewWeightGain)
        }
    }

    private fun enableCardView(cardView: MaterialCardView) {
        cardView.isChecked = true
        binding.buttonNextFragment.isEnabled = true
    }
}