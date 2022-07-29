package ilya.chistousov.countcalories.presentation.meal.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentFoodDetailBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.presentation.meal.fragment.FoodDetailDialogFragment.CaloriesInNutrition.*
import ilya.chistousov.countcalories.presentation.meal.viewmodel.AddFoodViewModel
import ilya.chistousov.countcalories.util.FLOAT_FORMAT
import java.text.DecimalFormat
import java.time.LocalDate
import kotlin.math.roundToInt

class FoodDetailDialogFragment : DialogFragment(R.layout.fragment_food_detail) {

    enum class CaloriesInNutrition {
        PROTEIN, FAT, CARBS
    }

    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding!!
    private val args: FoodDetailDialogFragmentArgs by navArgs()
    private lateinit var viewModel: AddFoodViewModel

    override fun onAttach(context: Context) {
        viewModel = context.appComponent.factory.create(AddFoodViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentFoodDetailBinding.bind(view)
        setupDialog()
        setDefaultFoodValue()
        initProgressBarAndTextViewPercent()
        changeFoodGramAmount()
        addFoodInDb()
        deleteFoodFromMeal()
        closeDialog()
    }

    private fun setupDialog() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun initProgressBarAndTextViewPercent() {
        with(binding) {
            progressProtein.progress = findPercent(PROTEIN, args.food.protein)
            proteinPercent.text = String.format(
                getString(R.string.food_detail_percent),
                findPercent(PROTEIN, args.food.protein).roundToInt().toString()
            )

            progressFat.progress = findPercent(FAT, args.food.fat)
            fatPercent.text = String.format(
                getString(
                    R.string.food_detail_percent
                ),
                findPercent(FAT, args.food.fat).roundToInt().toString()
            )

            progressCarbs.progress = findPercent(CARBS, args.food.carbs)
            carbsPercent.text = String.format(
                getString(R.string.food_detail_percent),
                findPercent(CARBS, args.food.carbs).roundToInt().toString()
            )
        }
    }

    private fun findPercent(nutrition: CaloriesInNutrition, value: Float): Float {
        return when (nutrition) {
            PROTEIN -> (((value * 4) / args.food.calories) * 100)
            FAT -> (((value * 9) / args.food.calories) * 100)
            CARBS -> (((value * 4) / args.food.calories) * 100)
        }
    }

    private fun changeFoodGramAmount() {
        with(binding) {
            inputGram.doOnTextChanged { text, _, _, _ ->
                if (text!!.isNotEmpty()) {
                    inputGramLayout.error = null
                    caloriesAmount.text = findNewCaloriesAmount(text.toString().toInt()).toString()
                    proteinAmount.text = DecimalFormat(FLOAT_FORMAT).format(
                        findNewNutritionValue(args.food.protein, text.toString().toInt())
                    ).toString()
                    fatAmount.text = DecimalFormat(FLOAT_FORMAT).format(
                        findNewNutritionValue(args.food.fat, text.toString().toInt())
                    ).toString()
                    carbsAmount.text = DecimalFormat(FLOAT_FORMAT).format(
                        findNewNutritionValue(args.food.carbs, text.toString().toInt())
                    ).toString()
                } else {
                    inputGramLayout.error = getString(R.string.error_input)
                    caloriesAmount.text = "0"
                    proteinAmount.text = "0.0"
                    fatAmount.text = "0.0"
                    carbsAmount.text = "0.0"
                }
            }
        }
    }

    private fun findNewNutritionValue(previousValue: Float, multiplier: Int): Float {
        return ((previousValue * multiplier) / 100)
    }

    private fun findNewCaloriesAmount(multiplier: Int): Int {
        return ((args.food.calories * multiplier) / 100)
    }

    private fun setDefaultFoodValue() {
        with(binding) {
            foodName.text = args.food.name
            inputGram.setText(args.food.gram.toString())
            caloriesAmount.text = args.food.calories.toString()
            proteinAmount.text = args.food.protein.toString()
            fatAmount.text = args.food.fat.toString()
            carbsAmount.text = args.food.carbs.toString()
        }
    }

    private fun addFoodInDb() {
        with(binding) {
            addFoodButton.setOnClickListener {
                if(inputGramLayout.error == null) {
                    val food = if (args.food.isCustom) {
                        createFood()
                    } else {
                        createFood(args.food.id)
                    }
                    viewModel.addFood(food)
                    findNavController().popBackStack(R.id.mealFragment, false)
                }
            }
        }
    }

    private fun createFood(foodId: Int = 0): Food {
        with(binding) {
            return Food(
                id = foodId,
                name = args.food.name,
                calories = caloriesAmount.text.toString().toInt(),
                protein = proteinAmount.text.toString().toFloat(),
                fat = fatAmount.text.toString().toFloat(),
                carbs = carbsAmount.text.toString().toFloat(),
                gram = inputGram.text.toString().toInt(),
                meal = args.meal,
                addedDate = LocalDate.now()
            )
        }
    }

    private fun deleteFoodFromMeal() {
        //checking if the food is saved in the db
        if (args.food.id != 0) {
            binding.deleteFoodButton.visibility = View.VISIBLE
            binding.deleteFoodButton.setOnClickListener {
                viewModel.deleteFood(args.food)
                findNavController().popBackStack()
            }
        }
    }

    private fun closeDialog() {
        binding.imageButtonExit.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}