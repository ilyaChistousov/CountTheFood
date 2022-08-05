package ilya.chistousov.countcalories.presentation.diary.fragment

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.*
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
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.diary.viewmodel.*
import ilya.chistousov.countcalories.presentation.tabs.TabsFragmentDirections
import ilya.chistousov.countcalories.util.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt
import kotlin.text.*

class DiaryFragment : BaseFragment<FragmentDiaryBinding>(
    FragmentDiaryBinding::inflate
) {

    private lateinit var diaryViewModel: DiaryViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private val dateViewModel: DateViewModel by viewModels()

    private var caloriesLeft = DEFAULT_INT_VALUE
    private var needProteins = DEFAULT_INT_VALUE
    private var needFats = DEFAULT_INT_VALUE
    private var needCarbs = DEFAULT_INT_VALUE

    override fun onAttach(context: Context) {
        diaryViewModel = context.appComponent.factory.create(DiaryViewModel::class.java)
        profileViewModel = context.appComponent.factory.create(ProfileViewModel::class.java)
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
            addDateText(it)
            getAllFood(it)
            showMealDetail(it)
            selectDate(it)
        }
    }

    private fun addDateText(currentDate: LocalDate) {
        val dayOfYear = LocalDate.now().dayOfYear
        with(binding.textViewDate) {
            text = when {
                dayOfYear == currentDate.dayOfYear -> "Сегодня"
                dayOfYear -1 == currentDate.dayOfYear -> "Вчера"
                dayOfYear +1 == currentDate.dayOfYear -> "Завтра"
                else -> {
                    val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale("ru"))
                    currentDate.format(formatter)
                }
            }

        }
    }


    private fun selectNextDay() {
        binding.imageViewNextDate.setOnClickListener {
            dateViewModel.selectNextDay()
            binding.innerLayout.startAnimation(addSlideAnimation(RIGHT_ANIM))
        }
    }

    private fun selectPreviousDay() {
        binding.imageViewPreviousDate.setOnClickListener {
            dateViewModel.selectPreviousDay()
            binding.innerLayout.startAnimation(addSlideAnimation(LEFT_ANIM))
        }
    }

    private fun selectDate(currentDate: LocalDate) {
        binding.textViewDate.setOnClickListener {
            val navDirection = DiaryFragmentDirections.actionDiaryFragmentToDatePickerDialogFragment(
                currentDate
            )
            findNavController().navigate(navDirection)
            setFragmentResultListener(DatePickerDialogFragment.REQUEST_DATE_KEY) { _, data ->
                val localDate = data.getSerializable(DatePickerDialogFragment.EXTRA_DATE) as LocalDate
                dateViewModel.setDate(localDate)
            }
            setFragmentResultListener(DatePickerDialogFragment.REQUEST_ANIM_KEY) { _, data ->
                val animDirection = data.getString(DatePickerDialogFragment.EXTRA_ANIM) as String
                binding.innerLayout.startAnimation(addSlideAnimation(animDirection))
            }
        }
    }

    private fun addSlideAnimation(direction: String): Animation {
        val animationId = when (direction) {
            LEFT_ANIM -> R.anim.slide_in_left
            RIGHT_ANIM -> R.anim.slide_in_right
            else -> throw IllegalArgumentException("Not such animation")
        }
        return AnimationUtils.loadAnimation(activity, animationId)
    }

    private fun showMealDetail(currentDate: LocalDate) {
        with(binding) {
            mealCardViewClickListener(currentDate)
        }
    }

    private fun mealCardViewClickListener(currentDate: LocalDate) {
        with(binding) {
            cardViewBreakfast.setOnClickListener {
                openMealFragment(
                    BREAKFAST,
                    currentDate,
                    getString(R.string.breakfast),
                    R.drawable.breakfast
                )
            }
            cardViewLunch.setOnClickListener {
                openMealFragment(
                    LUNCH,
                    currentDate,
                    getString(R.string.lunch),
                    R.drawable.lunch
                )
            }
            cardViewDinner.setOnClickListener {
                openMealFragment(
                    DINNER,
                    currentDate,
                    getString(R.string.dinner),
                    R.drawable.dinner
                )
            }
            cardViewSnack.setOnClickListener {
                openMealFragment(
                    SNACK,
                    currentDate,
                    getString(R.string.snack),
                    R.drawable.snack
                )
            }
        }
    }

    private fun openMealFragment(
        meal: Meal,
        currentDate: LocalDate,
        mealName: String,
        mealIconId: Int
    ) {
        val direction = TabsFragmentDirections.actionTabsFragmentToMealFragment(
            meal, currentDate, mealName, mealIconId
        )
        findTopNavController().navigate(direction)
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
        var calories = DEFAULT_INT_VALUE
        foods.forEach { calories += it.calories }

        when (meal) {
            BREAKFAST -> binding.tvBreakfastCalories.text =
                String.format(getString(R.string.caloriesAmount), calories.toString())
            LUNCH -> binding.tvLunchCalories.text =
                String.format(getString(R.string.caloriesAmount), calories.toString())
            DINNER -> binding.tvDinnerCalories.text =
                String.format(getString(R.string.caloriesAmount), calories.toString())
            SNACK -> binding.tvSnackCalories.text =
                String.format(getString(R.string.caloriesAmount), calories.toString())
            else -> throw IllegalArgumentException("food must have a meal")
        }
    }

    private fun updateMainCardInfo(foods: List<Food>) {
        var currentCalories = DEFAULT_INT_VALUE
        var currentProteins = DEFAULT_INT_VALUE
        var currentFats = DEFAULT_INT_VALUE
        var currentCarbs = DEFAULT_INT_VALUE

        foods.forEach {
            currentCalories += it.calories
            currentProteins += it.protein.toInt()
            currentFats += it.fat.toInt()
            currentCarbs += it.carbs.toInt()
        }

        with(binding.cardViewSummary) {
            textViewCaloriesAmount.text =
                String.format(getString(R.string.calories_amount_main), currentCalories, caloriesLeft)
            textViewProteinsCount.text =
                String.format(getString(R.string.proteins_amount_in_grams), currentProteins, needProteins)
            textViewFatsCount.text =
                String.format(getString(R.string.proteins_amount_in_grams), currentFats, needFats)
            textViewCarbsCount.text =
                String.format(getString(R.string.proteins_amount_in_grams), currentCarbs, needCarbs)
        }

        updateProgressBar(currentCalories, currentProteins, currentFats, currentCarbs)
    }

    private fun getCurrentProfile() {
        profileViewModel.currentProfile.observe(viewLifecycleOwner) {
            val age = it.birthDate.year
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
            progressCalories.backgroundProgressBarColor = getColor(requireContext(), R.color.porcelain_white)
            progressCalories.progressBarWidth = 10F
            progressCalories.backgroundProgressBarWidth = 10F
            progressCalories.progressBarColor = getColor(requireContext(), R.color.green_gray)
            progressCalories.progressBarColorEnd = getColor(requireContext(), R.color.light_gray)

            progressProteins.max = needProteins
            progressFats.max = needFats
            progressCarbs.max = needCarbs

        }
    }

    private fun updateProgressBar(calories: Int, proteins: Int, fats: Int, carbs: Int) {
        with(binding.cardViewSummary) {
            progressCalories.setProgressWithAnimation(calories.toFloat())
            progressProteins.setProgress(proteins, true)
            progressFats.setProgress(fats, true)
            progressCarbs.setProgress(carbs, true)
        }
    }

    private fun getNecessaryCalories(
        gender: Gender,
        weight: Float,
        growth: Int,
        age: Int,
        activityLevel: ActivityLevel
    ): Int {
        val caloriesForKeepingWeight = if (gender == Gender.FEMALE) {
            ((10 * weight) + (6.25 * growth) - (5 * age) - 161) * getActivityLevelMultiplier(activityLevel)
        } else {
            ((10 * weight) + (6.25 * growth) - (5 * age) + 5) * getActivityLevelMultiplier(activityLevel)
        }
        return caloriesForKeepingWeight.roundToInt()
    }

    private fun getActivityLevelMultiplier(activityLevel: ActivityLevel): Float {
        return when (activityLevel) {
            PASSIVE -> 1.2F
            INACTIVE -> 1.375F
            ACTIVE -> 1.55F
            HEAVILY_ACTIVE -> 1.725F
            EXTRA_ACTIVE -> 1.9F
        }
    }
}