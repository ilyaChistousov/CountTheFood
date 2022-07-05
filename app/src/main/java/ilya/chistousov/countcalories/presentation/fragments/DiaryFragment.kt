package ilya.chistousov.countcalories.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDiaryBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.model.Meal.*
import ilya.chistousov.countcalories.presentation.fragments.DatePickerDialogFragment.Companion.getFormattedDate
import ilya.chistousov.countcalories.presentation.util.filterListFoodByMeal
import ilya.chistousov.countcalories.presentation.viewmodels.FoodViewModel
import ilya.chistousov.countcalories.presentation.fragments.MealFragment.Companion.DEFAULT_VALUE
import java.text.SimpleDateFormat
import java.util.*


class DiaryFragment : Fragment(R.layout.fragment_diary) {

    private lateinit var binding: FragmentDiaryBinding
    private val viewModel: FoodViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[FoodViewModel::class.java]
    }

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            calendar.time = savedInstanceState.getSerializable(CURRENT_DATE_KEY) as Date
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiaryBinding.bind(view)
        showMealDetail()
        observeViewModel()
        setDefaultDate()
        selectDate()
        selectNextDay()
        selectPreviousDay()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(CURRENT_DATE_KEY, calendar.time)
    }

    private fun showMealDetail() {
        binding.cardViewBreakfast.setOnClickListener {
            openMealFragment(BREAKFAST)
        }
        binding.cardViewLunch.setOnClickListener {
            openMealFragment(LUNCH)
        }
        binding.cardViewDinner.setOnClickListener {
            openMealFragment(DINNER)
        }
        binding.cardViewSnack.setOnClickListener {
            openMealFragment(SNACK)
        }
    }

    private fun openMealFragment(meal: Meal) {
        val direction = DiaryFragmentDirections.actionDiaryFragmentToMealFragment(meal)
        findNavController().navigate(direction)
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
            textViewCaloriesCount.text =
                String.format(resources.getString(R.string.caloriesAmountMain), caloriesSum.toString(), 10000)
            textViewProteinsCount.text =
                String.format(resources.getString(R.string.amountInGrams), proteinsSum.toString())
            textViewFatsCount.text = String.format(resources.getString(R.string.amountInGrams), fatsSum.toString())
            textViewCarbsCount.text = String.format(resources.getString(R.string.amountInGrams), carbsSum.toString())
        }
    }

    private fun setDefaultDate() {
        val simpleDateFormat = getFormattedDate(DATE_PATTERN).format(calendar.time)

        binding.textViewDate.text = simpleDateFormat
    }

    private fun selectNextDay() {
        binding.imageViewNextDate.setOnClickListener {
            calendar.add(Calendar.DATE, 1)
            val simpleDateFormat = getFormattedDate(DATE_PATTERN).format(calendar.time)

            binding.textViewDate.text = simpleDateFormat
        }
    }

    private fun selectPreviousDay() {
        binding.imageViewPreviousDate.setOnClickListener {
            calendar.add(Calendar.DATE, -1)
            val simpleDateFormat = getFormattedDate(DATE_PATTERN).format(calendar.time)
            binding.textViewDate.text = simpleDateFormat
        }
    }


    private fun selectDate() {
        binding.textViewDate.setOnClickListener {
            val direction = DiaryFragmentDirections.actionDiaryFragmentToDatePickerDialogFragment(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            findNavController().navigate(direction)
            setFragmentResultListener(DatePickerDialogFragment.REQUEST_KEY) { _, data ->
                val date = data.getString(DatePickerDialogFragment.EXTRA_DATE)
                val parse = getFormattedDate().parse(date!!)
                calendar.time = parse!!
                binding.textViewDate.text = getFormattedDate(DATE_PATTERN).format(calendar.time)
            }
        }
    }

    companion object {
        private const val CURRENT_DATE_KEY = "Current date key"
        private const val DATE_PATTERN = "EEE, d MMM"
    }

}