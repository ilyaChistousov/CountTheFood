package ilya.chistousov.countcalories.presentation.foods.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDiaryBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.domain.model.Meal.*
import ilya.chistousov.countcalories.presentation.foods.viewmodels.FoodViewModel
import ilya.chistousov.countcalories.presentation.foods.fragments.MealFragment.Companion.DEFAULT_VALUE
import ilya.chistousov.countcalories.presentation.util.filterListFoodByMealAndDate
import ilya.chistousov.countcalories.presentation.foods.viewmodels.DateViewModel
import java.text.SimpleDateFormat
import java.util.*


class DiaryFragment : BaseFragment<FragmentDiaryBinding>(
    FragmentDiaryBinding::inflate
)    {

    private val foodViewModel: FoodViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[FoodViewModel::class.java]
    }

    private val dateViewModel: DateViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentDate()
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

    private fun setDateToDiaryFragment(currentDate: Date) {
        val simpleDateFormat = SimpleDateFormat(
            DATE_PATTERN,
            Locale("ru")
        ).format(currentDate)
        binding.textViewDate.text = simpleDateFormat
    }

    private fun showMealDetail(currentDate: Date) {
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

    private fun openMealFragment(meal: Meal, currentDate: Date) {
        val direction = DiaryFragmentDirections.actionDiaryFragmentToMealFragment(meal, currentDate)
        findNavController().navigate(direction)
    }

    private fun getAllFood(currentDate: Date) {
        foodViewModel.foods.observe(viewLifecycleOwner) {
            val currentDayFoods = filterListFoodByDate(it, currentDate)
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
            textViewCaloriesCount.text =
                String.format(resources.getString(R.string.caloriesAmountMain), caloriesSum.toString(), 10000)
            textViewProteinsCount.text =
                String.format(resources.getString(R.string.amountInGrams), proteinsSum.toString())
            textViewFatsCount.text = String.format(resources.getString(R.string.amountInGrams), fatsSum.toString())
            textViewCarbsCount.text = String.format(resources.getString(R.string.amountInGrams), carbsSum.toString())
        }
    }


    private fun selectDate(currentDate: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        binding.textViewDate.setOnClickListener {
            val direction = DiaryFragmentDirections.actionDiaryFragmentToDatePickerDialogFragment(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            findNavController().navigate(direction)
            setFragmentResultListener(DatePickerDialogFragment.REQUEST_KEY) { _, data ->
                val date = data.getSerializable(DatePickerDialogFragment.EXTRA_DATE) as Date
                dateViewModel.setDate(date)
            }
        }
    }

    private fun filterListFoodByDate(foods: List<Food>, currentDate: Date) : List<Food>{
        return foods.filter { it.addedDate == currentDate }
    }


    companion object {
        private const val DATE_PATTERN = "EEE, d MMM"
    }

}