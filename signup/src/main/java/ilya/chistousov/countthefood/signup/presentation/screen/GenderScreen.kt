package ilya.chistousov.countthefood.signup.presentation.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.card.MaterialCardView
import ilya.chistousov.countthefood.core.model.Gender
import ilya.chistousov.countthefood.core.model.Gender.FEMALE
import ilya.chistousov.countthefood.core.model.Gender.MALE
import ilya.chistousov.countthefood.signup.databinding.FragmentGenderBinding
import ilya.chistousov.countthefood.signup.presentation.fragment.SignUpFragmentContainer.Companion.FOURTH_SCREEN
import ilya.chistousov.countthefood.signup.utils.GENDER

class GenderScreen
    : BaseScreen<FragmentGenderBinding>(
    FragmentGenderBinding::inflate
){
    private var selectedGender: Gender? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        restoreData()
        setNextScreen()
        selectGender()
    }

    private fun restoreData() {
        if(selectedGender != null) {
            getSelectedGender(selectedGender!!)
            binding.buttonNextFragment.isEnabled = true
        }
    }

    private fun selectGender() {
        selectMale()
        selectFemale()
    }

    private fun setNextScreen() {
        binding.buttonNextFragment.setOnClickListener {
            createProfileViewModel.putString(GENDER, selectedGender!!.name)
            parentBinding.viewPager.currentItem = FOURTH_SCREEN
        }
    }

    private fun selectMale() {
        binding.maleGender.setOnClickListener {
            binding.maleGender.isChecked = true
            binding.femaleGender.isChecked = false
            binding.buttonNextFragment.isEnabled = true
            selectedGender = MALE
            Log.d("GENDER", "select male = $selectedGender.toString()")
        }
    }

    private fun selectFemale() {
        binding.femaleGender.setOnClickListener {
            binding.maleGender.isChecked = false
            binding.femaleGender.isChecked = true
            binding.buttonNextFragment.isEnabled = true
            selectedGender = FEMALE
            Log.d("GENDER", "select female = $selectedGender.toString()")
        }
    }

    private fun getSelectedGender(gender: Gender) {
        when (gender) {
            MALE -> enableCardView(binding.maleGender)
            FEMALE -> enableCardView(binding.femaleGender)
        }
    }

    private fun enableCardView(cardView: MaterialCardView) {
        cardView.isChecked = true
        binding.buttonNextFragment.isEnabled = true
    }
}