package ilya.chistousov.countcalories.presentation.register.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentGoalBinding
import ilya.chistousov.countcalories.domain.model.Goal.*
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModel
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModelFactory
import javax.inject.Inject

class GoalScreen
    : BaseScreen<FragmentGoalBinding>(
    FragmentGoalBinding::inflate
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
        selectWeightLoss()
        selectKeepingCurrentWeight()
        selectWeightGain()
        goingToNextFragment()
        defaultButtonState()
        getSelectedGoal()
        backToLoginMenu()
    }

    private fun selectWeightLoss() {
        binding.cardViewWeightLoss.setOnClickListener {
            binding.cardViewWeightLoss.isChecked = true
            binding.cardViewWeightGain.isChecked = false
            binding.cardViewKeepingCurrentWeight.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setGoal(WEIGHT_LOSS)
        }
    }

    private fun selectKeepingCurrentWeight() {
        binding.cardViewKeepingCurrentWeight.setOnClickListener {
            binding.cardViewKeepingCurrentWeight.isChecked = true
            binding.cardViewWeightLoss.isChecked = false
            binding.cardViewWeightGain.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setGoal(KEEPING_CURRENT_WEIGHT)
            Bundle().putSerializable("GOAL", KEEPING_CURRENT_WEIGHT)
        }
    }

    private fun selectWeightGain() {
        binding.cardViewWeightGain.setOnClickListener {
            binding.cardViewWeightGain.isChecked = true
            binding.cardViewWeightLoss.isChecked = false
            binding.cardViewKeepingCurrentWeight.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setGoal(WEIGHT_GAIN)
        }
    }

    private fun defaultButtonState() {
        binding.buttonNextFragment.isEnabled = false
    }

    private fun goingToNextFragment() {
        binding.buttonNextFragment.setOnClickListener {
            parentBinding.viewPager.currentItem = 1
        }
    }

    private fun getSelectedGoal() {
        createProfileViewModel.goal.observe(viewLifecycleOwner) {
            when (it) {
                WEIGHT_LOSS -> enableCardView(binding.cardViewWeightLoss)
                KEEPING_CURRENT_WEIGHT -> enableCardView(binding.cardViewKeepingCurrentWeight)
                WEIGHT_GAIN -> enableCardView(binding.cardViewWeightGain)
                null -> {}
            }
        }
    }

    private fun enableCardView(cardView: MaterialCardView) {
        cardView.isChecked = true
        binding.buttonNextFragment.isEnabled = true
    }

    private fun backToLoginMenu() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


}