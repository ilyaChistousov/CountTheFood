package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import ilya.chistousov.countcalories.databinding.FragmentGenderBinding
import ilya.chistousov.countcalories.domain.model.Gender.FEMALE
import ilya.chistousov.countcalories.domain.model.Gender.MALE
import ilya.chistousov.countcalories.presentation.register.viewmodel.ProfileViewModel

class GenderScreen
    : BaseScreen<FragmentGenderBinding>(
    FragmentGenderBinding::inflate
){
    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[ProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        defaultButtonState()
        goingToNextFragment()
        selectMale()
        selectFemale()
        getSelectedGender()
        showPreviousScreen()
    }

    private fun selectMale() {
        binding.maleGender.setOnClickListener {
            binding.maleGender.isChecked = true
            binding.femaleGender.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            profileViewModel.setGender(MALE)
        }
    }

    private fun selectFemale() {
        binding.femaleGender.setOnClickListener {
            binding.femaleGender.isChecked = true
            binding.maleGender.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            profileViewModel.setGender(FEMALE)
        }
    }


    private fun defaultButtonState() {
        binding.buttonNextFragment.isEnabled = false
    }

    private fun goingToNextFragment() {
        binding.buttonNextFragment.setOnClickListener {
            parentBinding.viewPager.currentItem = 3
        }
    }

    private fun getSelectedGender() {
        profileViewModel.gender.observe(viewLifecycleOwner) {
            when(it) {
                FEMALE -> enableCardView(binding.femaleGender)
                MALE -> enableCardView(binding.maleGender)
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
            parentBinding.viewPager.currentItem = 1
        }
    }

}