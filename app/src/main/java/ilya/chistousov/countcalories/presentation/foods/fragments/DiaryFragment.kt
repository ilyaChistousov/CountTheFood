package ilya.chistousov.countcalories.presentation.foods.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDiaryBinding
import ilya.chistousov.countcalories.domain.model.ActivityLevel
import ilya.chistousov.countcalories.domain.model.ActivityLevel.*
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Gender
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.model.Meal.*
import ilya.chistousov.countcalories.presentation.foods.fragments.MealFragment.Companion.DEFAULT_VALUE
import ilya.chistousov.countcalories.presentation.foods.viewmodels.DateViewModel
import ilya.chistousov.countcalories.presentation.foods.viewmodels.FoodViewModel
import ilya.chistousov.countcalories.presentation.foods.viewmodels.ProfileViewModel
import ilya.chistousov.countcalories.presentation.util.filterListFoodByDate
import ilya.chistousov.countcalories.presentation.util.filterListFoodByMealAndDate
import ilya.chistousov.countcalories.presentation.util.getYearFromDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.text.*


class DiaryFragment : BaseFragment<FragmentDiaryBinding>(
    FragmentDiaryBinding::inflate
) {

    private val foodViewModel: FoodViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[FoodViewModel::class.java]
    }

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[ProfileViewModel::class.java]
    }

    private val dateViewModel: DateViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentDate()
        getCurrentProfile()
    }

    private fun getCurrentDate() {
        dateViewModel.currentDate.observe(viewLifecycleOwner) {
            selectNextDay()
            selectPreviousDay()
            setDateToDiaryFragment(it)
            getAllFood(it)
            showMealDetail(it)
            selectDate(it)
        }
    }

    private fun selectNextDay() {
        binding.imageViewNextDate.setOnClickListener {
            dateViewModel.selectNextDay()
        }
    }

    private fun selectPreviousDay() {
        binding.imageViewPreviousDate.setOnClickListener {
            dateViewModel.selectPreviousDay()
        }
    }

    private fun setDateToDiaryFragment(currentDate: LocalDate) {
        val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale("ru"))
        binding.textViewDate.text = currentDate.format(formatter)
    }

    private fun showMealDetail(currentDate: LocalDate) {
        binding.cardViewBreakfast.setOnClickListener {
            openMealFragment(BREAKFAST, currentDate)
        }
        binding.cardViewLunch.setOnClickListener {
            openMealFragment(LUNCH, currentDate)
        }
        binding.cardViewDinner.setOnClickListener {
            openMealFragment(DINNER, currentDate)
        }
        binding.cardViewSnack.setOnClickListener {
            openMealFragment(SNACK, currentDate)
        }
    }

    private fun openMealFragment(meal: Meal, currentDate: LocalDate) {
        val direction = DiaryFragmentDirections.actionDiaryFragmentToMealFragment(meal, currentDate)
        findNavController().navigate(direction)
    }

    private fun getAllFood(currentDate: LocalDate) {
        foodViewModel.foods.observe(viewLifecycleOwner) {
            val currentDayFoods = it.filterListFoodByDate(currentDate)
            updateMainCardInfo(currentDayFoods)

            val breakfastFoodList = it.filterListFoodByMealAndDate(BREAKFAST, currentDate)
            updateUiInCardMeal(breakfastFoodList, BREAKFAST)

            val lunchFoodList = it.filterListFoodByMealAndDate(LUNCH, currentDate)
            updateUiInCardMeal(lunchFoodList, LUNCH)

            val dinnerFoodList = it.filterListFoodByMealAndDate(DINNER, currentDate)
            updateUiInCardMeal(dinnerFoodList, DINNER)

            val snackFoodList = it.filterListFoodByMealAndDate(SNACK, currentDate)
            updateUiInCardMeal(snackFoodList, SNACK)
        }
    }

    private fun updateUiInCardMeal(foods: List<Food>, meal: Meal) {
        var calories = DEFAULT_VALUE.toInt()

        foods.forEach { calories += it.calories }

        when (meal) {
            BREAKFAST -> binding.tvBreakfastCalories.text =
                String.format(resources.getString(R.string.caloriesAmount), calories.toString())
            LUNCH -> binding.tvLunchCalories.text =
                String.format(resources.getString(R.string.caloriesAmount), calories.toString())
            DINNER -> binding.tvDinnerCalories.text =
                String.format(resources.getString(R.string.caloriesAmount), calories.toString())
            SNACK -> binding.tvSnackCalories.text =
                String.format(resources.getString(R.string.caloriesAmount), calories.toString())
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

        with(binding.cardViewSummary) {
            textViewCurrentCalories.text =
                String.format(getString(R.string.calories_eaten), caloriesSum)
            textViewProteinsCount.text =
                String.format(getString(R.string.amount_in_grams), proteinsSum)
            textViewFatsCount.text = String.format(resources.getString(R.string.amount_in_grams), fatsSum)
            textViewCarbsCount.text = String.format(resources.getString(R.string.amount_in_grams), carbsSum)
        }
    }


    private fun selectDate(currentDate: LocalDate) {
        binding.textViewDate.setOnClickListener {
            val navDirection = DiaryFragmentDirections.actionDiaryFragmentToDatePickerDialogFragment(
                currentDate.year,
                currentDate.monthValue - 1,
                currentDate.dayOfMonth
            )
            findNavController().navigate(navDirection)
            setFragmentResultListener(DatePickerDialogFragment.REQUEST_KEY) { _, data ->
                val localDate = data.getSerializable(DatePickerDialogFragment.EXTRA_DATE) as LocalDate
                dateViewModel.setDate(localDate)
            }
        }
    }

    private fun getCurrentProfile() {
        profileViewModel.currentProfile.observe(viewLifecycleOwner) {
            val age = it.birthDate.getYearFromDate()
            val requiredAmountCalories =
                getFormulaForWeightLoss(it.gender, it.currentWeight, it.currentGrowth, age, it.activityLevel)
            binding.cardViewSummary.textViewCaloriesAmount.text =
                String.format(getString(R.string.calories_amount_main), requiredAmountCalories)
        }
    }

    private fun getFormulaForWeightLoss(
        gender: Gender,
        weight: Int,
        growth: Int,
        age: Int,
        activityLevel: ActivityLevel
    ): Int {
        val caloriesForKeepingWeight = if (gender == Gender.FEMALE) {
            ((10 * weight) + (6.25 * growth) - (5 * age) - 161) * getActivityLevelMultiplier(activityLevel)
        } else {
            ((10 * weight) + (6.25 * growth) - (5 * age) + 5) * getActivityLevelMultiplier(activityLevel)
        }
        return caloriesForKeepingWeight.toInt()
    }

    private fun getActivityLevelMultiplier(activityLevel: ActivityLevel): Double {
        return when (activityLevel) {
            PASSIVE -> 1.2
            INACTIVE -> 1.375
            ACTIVE -> 1.55
            HEAVILY_ACTIVE -> 1.725
            EXTRA_ACTIVE -> 1.9
        }
    }


    companion object {
        private const val DATE_PATTERN = "EEE, d MMM"
    }

}