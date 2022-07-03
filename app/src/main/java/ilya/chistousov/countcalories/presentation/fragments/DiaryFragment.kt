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
import ilya.chistousov.countcalories.presentation.fragments.BaseMealFragment.Companion.DEFAULT_VALUE


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
            updateMainCardInfo(it)

            val breakfastFoodList = it.filterListFoodByMeal(BREAKFAST)
            updateUiInEachMeal(breakfastFoodList, BREAKFAST)

            val lunchFoodList = it.filterListFoodByMeal(LUNCH)
            updateUiInEachMeal(lunchFoodList, LUNCH)

            val dinnerFoodList = it.filterListFoodByMeal(DINNER)
            updateUiInEachMeal(dinnerFoodList, DINNER)

            val snackFoodList = it.filterListFoodByMeal(SNACK)
            updateUiInEachMeal(snackFoodList, SNACK)
        }
    }

    private fun updateUiInEachMeal(foods: List<Food>, meal: Meal) {
        var calories = DEFAULT_VALUE.toInt()

        foods.forEach{ calories += it.calories}

        when(meal) {
            BREAKFAST -> binding.tvBreakfastCalories.text = String.format(resources.getString(R.string.caloriesAmount), calories.toString())
            LUNCH -> binding.tvLunchCalories.text = String.format(resources.getString(R.string.caloriesAmount), calories.toString())
            DINNER -> binding.tvDinnerCalories.text = String.format(resources.getString(R.string.caloriesAmount), calories.toString())
            SNACK -> binding.tvSnackCalories.text = String.format(resources.getString(R.string.caloriesAmount), calories.toString())
        }
    }

    private fun updateMainCardInfo(foods: List<Food>) {
        var caloriesSum = DEFAULT_VALUE.toInt()
        var proteinsSum = DEFAULT_VALUE
        var fatsSum = DEFAULT_VALUE
        var carbsSum = DEFAULT_VALUE

        foods.forEach {
            caloriesSum += it.calories
            proteinsSum += it.proteins
            fatsSum += it.fats
            carbsSum += it.carbs
        }

        with(binding) {
            textViewCaloriesCount.text = String.format(resources.getString(R.string.caloriesAmountMain), caloriesSum.toString(), 10000)
            textViewProteinsCount.text = String.format(resources.getString(R.string.amountInGrams), proteinsSum.toString())
            textViewFatsCount.text = String.format(resources.getString(R.string.amountInGrams), fatsSum.toString())
            textViewCarbsCount.text = String.format(resources.getString(R.string.amountInGrams), carbsSum.toString())
        }

    }


}