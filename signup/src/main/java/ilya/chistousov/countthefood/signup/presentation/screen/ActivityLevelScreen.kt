package ilya.chistousov.countthefood.signup.presentation.screen

import android.os.Bundle
import android.view.View
import com.google.android.material.card.MaterialCardView
import ilya.chistousov.countthefood.core.model.ActivityLevel
import ilya.chistousov.countthefood.core.model.ActivityLevel.*
import ilya.chistousov.countthefood.signup.databinding.FragmentActivityLevelBinding
import ilya.chistousov.countthefood.signup.presentation.fragment.SignUpFragmentContainer.Companion.THIRD_SCREEN
import ilya.chistousov.countthefood.signup.presentation.screen.BaseScreen
import ilya.chistousov.countthefood.signup.utils.ACTIVITY_LEVEL

class ActivityLevelScreen
    : BaseScreen<FragmentActivityLevelBinding>(
    FragmentActivityLevelBinding::inflate
) {
    private var activityLevel: ActivityLevel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restoreData()
        setNextScreen()
        selectActivityLevel()
    }


    private fun restoreData() {
        if (activityLevel != null) {
            getSelectedActivity(activityLevel!!)
            binding.buttonNextFragment.isEnabled = true
        }
    }

    private fun setNextScreen() {
        binding.buttonNextFragment.setOnClickListener {
            parentBinding.viewPager.currentItem = THIRD_SCREEN
            createProfileViewModel.putString(ACTIVITY_LEVEL, activityLevel!!.name)
        }
    }

    private fun selectActivityLevel() {
        selectPassive()
        selectInactive()
        selectActive()
        selectHeavilyActive()
        selectExtraActive()
    }

    private fun selectPassive() {
        binding.passiveActivity.setOnClickListener {
            setEnable(binding.passiveActivity)
            activityLevel = PASSIVE
        }
    }

    private fun selectInactive() {
        binding.inactiveActivity.setOnClickListener {
            setEnable(binding.inactiveActivity)
            activityLevel = INACTIVE
        }
    }

    private fun selectActive() {
        binding.activeActivity.setOnClickListener {
            setEnable(binding.activeActivity)
            activityLevel = ACTIVE
        }
    }

    private fun selectHeavilyActive() {
        binding.heavilyActiveActivity.setOnClickListener {
            setEnable(binding.heavilyActiveActivity)
            activityLevel = HEAVILY_ACTIVE
        }
    }

    private fun selectExtraActive() {
        binding.extraActiveActivity.setOnClickListener {
            setEnable(binding.extraActiveActivity)
            activityLevel = EXTRA_ACTIVE
        }
    }

    private fun setEnable(materialCard: MaterialCardView) {
        binding.passiveActivity.isChecked = false
        binding.inactiveActivity.isChecked = false
        binding.activeActivity.isChecked = false
        binding.heavilyActiveActivity.isChecked = false
        binding.extraActiveActivity.isChecked = false
        materialCard.isChecked = true
        binding.buttonNextFragment.isEnabled = true
    }

    private fun getSelectedActivity(activityLevel: ActivityLevel) {
        with(binding) {
            when(activityLevel) {
                PASSIVE -> enableCardView(passiveActivity)
                INACTIVE -> enableCardView(inactiveActivity)
                ACTIVE -> enableCardView(activeActivity)
                HEAVILY_ACTIVE -> enableCardView(heavilyActiveActivity)
                EXTRA_ACTIVE -> enableCardView(extraActiveActivity)
            }
        }
    }

    private fun enableCardView(cardView: MaterialCardView) {
        cardView.isChecked = true
        binding.buttonNextFragment.isEnabled = true
    }
}