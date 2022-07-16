package ilya.chistousov.countcalories.presentation.foods.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentDiaryBinding
import ilya.chistousov.countcalories.domain.model.*
import ilya.chistousov.countcalories.domain.model.ActivityLevel.*
import ilya.chistousov.countcalories.domain.model.Goal.*
import ilya.chistousov.countcalories.domain.model.Meal.*
import ilya.chistousov.countcalories.presentation.foods.fragments.MealFragment.Companion.DEFAULT_VALUE
import ilya.chistousov.countcalories.presentation.foods.viewmodels.*
import ilya.chistousov.countcalories.presentation.util.filterListFoodByDate
import ilya.chistousov.countcalories.presentation.util.filterListFoodByMealAndDate
import ilya.chistousov.countcalories.presentation.util.getYearFromDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.text.*

class DiaryFragment : BaseFragment<FragmentDiaryBinding>(
    FragmentDiaryBinding::inflate
) {

    private val diaryViewModel: DiaryViewModel by viewModels {
        diaryFactory.create()
    }
    private val profileViewModel: ProfileViewModel by viewModels {
        profileFactory.create()
    }
    private val dateViewModel: DateViewModel by viewModels()

    @Inject
    lateinit var profileFactory: ProfileViewModelFactory.Factory

    @Inject
    lateinit var diaryFactory: DiaryViewModelFactory.Factory

    private var caloriesLeft: Int = 0
    private var needProteins: Int = 0
    private var needFats: Int = 0
    private var needCarbs: Int = 0

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

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
        diaryViewModel.foods.observe(viewLifecycleOwner) {
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
        var currentCalories = DEFAULT_VALUE
        var currentProteins = DEFAULT_VALUE
        var currentFats = DEFAULT_VALUE
        var currentCarbs = DEFAULT_VALUE

        foods.forEach {
            currentCalories += it.calories
            currentProteins += it.proteins.toInt()
            currentFats += it.fats.toInt()
            currentCarbs += it.carbs.toInt()
        }

        with(binding.cardViewSummary) {
            textViewCaloriesAmount.text =
                String.format(getString(R.string.calories_amount_main), currentCalories, caloriesLeft)
            textViewProteinsCount.text =
                String.format(getString(R.string.proteins_amount_in_grams), currentProteins, needProteins)
            textViewFatsCount.text =
                String.format(resources.getString(R.string.proteins_amount_in_grams), currentFats, needFats)
            textViewCarbsCount.text =
                String.format(resources.getString(R.string.proteins_amount_in_grams), currentCarbs, needCarbs)
        }

        updateProgressBar(currentCalories, currentProteins, currentFats, currentCarbs)
    }

    private fun selectDate(currentDate: LocalDate) {
        binding.textViewDate.setOnClickListener {
            val navDirection = DiaryFragmentDirections.actionDiaryFragmentToDatePickerDialogFragment(
                currentDate
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
            caloriesLeft = when (it.goal) {
                KEEPING_CURRENT_WEIGHT -> {
                    getNecessaryCalories(it.gender, it.currentWeight, it.currentGrowth, age, it.activityLevel)
                }
                WEIGHT_LOSS -> {
                    getNecessaryCalories(it.gender, it.currentWeight, it.currentGrowth, age, it.activityLevel) - 500
                }
                WEIGHT_GAIN -> {
                    getNecessaryCalories(it.gender, it.currentWeight, it.currentGrowth, age, it.activityLevel) + 500
                }
            }

            needProteins = getNecessaryProteins()
            needFats = getNecessaryFats()
            needCarbs = getNecessaryCarbs()

            setMaxProgressBar()
        }
    }

    private fun getNecessaryProteins(): Int {
        return (caloriesLeft * 0.2 / 4).toInt()
    }

    private fun getNecessaryFats(): Int {
        return (caloriesLeft * 0.3 / 9).toInt()
    }

    private fun getNecessaryCarbs(): Int {
        return (caloriesLeft * 0.5 / 4).toInt()
    }

    private fun setMaxProgressBar() {
        with(binding.cardViewSummary) {
            progressCalories.progressMax = caloriesLeft.toFloat()
            progressCalories.progressBarColor = Color.RED
            progressCalories.progressBarColorEnd = Color.BLUE

            progressProteins.max = needProteins
            progressFats.max = needFats
            progressCarbs.max = needCarbs
        }
    }

    private fun updateProgressBar(calories: Int, proteins: Int, fats: Int, carbs: Int) {
        with(binding.cardViewSummary) {
            progressCalories.progress = calories.toFloat()
            progressProteins.setProgress(proteins, true)
            progressFats.setProgress(fats, true)
            progressCarbs.setProgress(carbs, true)
        }
    }


    private fun getNecessaryCalories(
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