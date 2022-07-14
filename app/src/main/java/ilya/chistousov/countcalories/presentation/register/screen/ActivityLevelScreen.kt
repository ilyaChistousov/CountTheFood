package ilya.chistousov.countcalories.presentation.register.screen


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import ilya.chistousov.countcalories.databinding.FragmentActivityLevelBinding
import ilya.chistousov.countcalories.domain.model.ActivityLevel.*
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModel

class ActivityLevelScreen
    : BaseScreen<FragmentActivityLevelBinding>(
    FragmentActivityLevelBinding::inflate
) {
    private val createProfileViewModel: CreateProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[CreateProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectPassive()
        selectInactive()
        selectActive()
        selectHeavilyActive()
        selectExtraActive()
        defaultButtonState()
        goingToNextFragment()
        getSelectedActivityLevel()
        showPreviousScreen()
    }

    private fun selectPassive() {
        binding.passiveLifestyle.setOnClickListener {
            binding.passiveLifestyle.isChecked = true
            binding.inactiveLifestyle.isChecked = false
            binding.activeLifestyle.isChecked = false
            binding.heavilyActiveLifestyle.isChecked = false
            binding.extraActiveLifestyle.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setActivityLevel(PASSIVE)
        }
    }

    private fun selectInactive() {
        binding.inactiveLifestyle.setOnClickListener {
            binding.inactiveLifestyle.isChecked = true
            binding.passiveLifestyle.isChecked = false
            binding.activeLifestyle.isChecked = false
            binding.heavilyActiveLifestyle.isChecked = false
            binding.extraActiveLifestyle.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setActivityLevel(INACTIVE)
        }
    }

    private fun selectActive() {
        binding.activeLifestyle.setOnClickListener {
            binding.activeLifestyle.isChecked = true
            binding.passiveLifestyle.isChecked = false
            binding.inactiveLifestyle.isChecked = false
            binding.heavilyActiveLifestyle.isChecked = false
            binding.extraActiveLifestyle.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setActivityLevel(ACTIVE)
        }
    }

    private fun selectHeavilyActive() {
        binding.heavilyActiveLifestyle.setOnClickListener {
            binding.heavilyActiveLifestyle.isChecked = true
            binding.passiveLifestyle.isChecked = false
            binding.inactiveLifestyle.isChecked = false
            binding.activeLifestyle.isChecked = false
            binding.extraActiveLifestyle.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setActivityLevel(HEAVILY_ACTIVE)
        }
    }

    private fun selectExtraActive() {
        binding.extraActiveLifestyle.setOnClickListener {
            binding.extraActiveLifestyle.isChecked = true
            binding.passiveLifestyle.isChecked = false
            binding.inactiveLifestyle.isChecked = false
            binding.activeLifestyle.isChecked = false
            binding.heavilyActiveLifestyle.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            createProfileViewModel.setActivityLevel(EXTRA_ACTIVE)
        }
    }

    private fun defaultButtonState() {
        binding.buttonNextFragment.isEnabled = false
    }

    private fun goingToNextFragment() {
        binding.buttonNextFragment.setOnClickListener {
            parentBinding.viewPager.currentItem = 2
        }
    }

    private fun getSelectedActivityLevel() {
        createProfileViewModel.activityLevel.observe(viewLifecycleOwner) {
            when(it) {
                PASSIVE -> enableCardView(binding.passiveLifestyle)
                INACTIVE -> enableCardView(binding.inactiveLifestyle)
                ACTIVE -> enableCardView(binding.activeLifestyle)
                HEAVILY_ACTIVE -> enableCardView(binding.heavilyActiveLifestyle)
                EXTRA_ACTIVE -> enableCardView(binding.extraActiveLifestyle)
                null -> {}
            }
        }
    }

    private fun enableCardView(cardView: MaterialCardView) {
        cardView.isChecked = true
        binding.buttonNextFragment.isEnabled = true
    }

    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            parentBinding.viewPager.currentItem = 0
        }
    }
}