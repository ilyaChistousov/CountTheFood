package ilya.chistousov.countcalories.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDiaryBinding
import java.lang.IllegalStateException


class DiaryFragment : Fragment(R.layout.fragment_diary) {

    private lateinit var binding: FragmentDiaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiaryBinding.bind(view)
        showBreakfastDetail()
        showDinnerDetail()
        showLunchDetail()
        showSnackDetail()
    }

    private fun navigateMealDetail(fragmentName : String) {
        val fragmentId = when(fragmentName) {
            BREAKFAST -> R.id.action_diaryFragment_to_breakfastFragment
            LUNCH -> R.id.action_diaryFragment_to_lunchFragment
            DINNER -> R.id.action_diaryFragment_to_dinnerFragment
            SNACK -> R.id.action_diaryFragment_to_snackFragment
            else -> throw IllegalStateException("Not such fragment = $fragmentName")
        }
        findNavController().navigate(fragmentId)
    }

    private fun showBreakfastDetail() {
        binding.cardViewBreakfast.setOnClickListener {
            navigateMealDetail(BREAKFAST)
        }
    }

    private fun showLunchDetail() {
        binding.cardViewLunch.setOnClickListener {
            navigateMealDetail(LUNCH)
        }
    }

    private fun showDinnerDetail() {
        binding.cardViewDinner.setOnClickListener {
            navigateMealDetail(DINNER)
        }
    }

    private fun showSnackDetail() {
        binding.cardViewSnack.setOnClickListener {
            navigateMealDetail(SNACK)
        }
    }

    companion object {
        private const val BREAKFAST = "Breakfast"
        private const val LUNCH = "Lunch"
        private const val DINNER = "Dinner"
        private const val SNACK = "Snack"
    }

}