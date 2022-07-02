package ilya.chistousov.countcalories.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDiaryBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.model.Meal.*
import ilya.chistousov.countcalories.presentation.util.filterListFoodByMeal
import ilya.chistousov.countcalories.presentation.viewmodels.FoodViewModel


class DiaryFragment : Fragment(R.layout.fragment_diary) {

    private lateinit var binding: FragmentDiaryBinding
    private val viewModel: FoodViewModel by lazy {
        ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(requireActivity().application))[FoodViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiaryBinding.bind(view)
        showBreakfastDetail()
        showDinnerDetail()
        showLunchDetail()
        showSnackDetail()
        observeViewModel()
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

    private fun navigateMealDetail(meal: Meal) {
        val fragmentId = when (meal) {
            BREAKFAST -> R.id.action_diaryFragment_to_breakfastFragment
            LUNCH -> R.id.action_diaryFragment_to_lunchFragment
            DINNER -> R.id.action_diaryFragment_to_dinnerFragment
            SNACK -> R.id.action_diaryFragment_to_snackFragment
        }
        findNavController().navigate(fragmentId)
    }

    private fun observeViewModel() {
        viewModel.foods.observe(viewLifecycleOwner) {
            val breakfastFoodList = it.filterListFoodByMeal(BREAKFAST)
            calculateInfoFromFood(breakfastFoodList, BREAKFAST)

            val lunchFoodList = it.filterListFoodByMeal(LUNCH)
            calculateInfoFromFood(lunchFoodList, LUNCH)

            val dinnerFoodList = it.filterListFoodByMeal(DINNER)
            calculateInfoFromFood(dinnerFoodList, DINNER)

            val snackFoodList = it.filterListFoodByMeal(SNACK)
            calculateInfoFromFood(snackFoodList, SNACK)
        }
    }

    private fun calculateInfoFromFood(foods: List<Food>, meal: Meal) {
        var caloriesSum = BaseMealFragment.DEFAULT_VALUE

        foods.forEach{ caloriesSum += it.calories}

        when(meal) {
            BREAKFAST -> binding.tvBreakfastCalories.text = caloriesSum.toString()
            LUNCH -> binding.tvLunchCalories.text = caloriesSum.toString()
            DINNER -> binding.tvDinnerCalories.text = caloriesSum.toString()
            SNACK -> binding.tvSnackCalories.text = caloriesSum.toString()
        }
    }


}