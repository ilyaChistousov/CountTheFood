package ilya.chistousov.countcalories.presentation.meal.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentFoodDetailBinding
import ilya.chistousov.countcalories.presentation.meal.fragment.FoodDetailDialogFragment.CaloriesInNutrition.*
import ilya.chistousov.countcalories.presentation.meal.viewmodel.AddFoodViewModel
import ilya.chistousov.countcalories.presentation.meal.viewmodel.SearchFoodViewModelFactory
import ilya.chistousov.countcalories.presentation.viewmodelfactory.MultiViewModelFactory
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.math.roundToInt

class FoodDetailDialogFragment : DialogFragment(R.layout.fragment_food_detail) {

    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding!!
    private val arg: FoodDetailDialogFragmentArgs by navArgs()
    private lateinit var viewModel: AddFoodViewModel

    override fun onAttach(context: Context) {
        viewModel = context.appComponent.factory.create(AddFoodViewModel::class.java)
        super.onAttach(context)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentFoodDetailBinding.bind(view)
        setFoodName()
        setFoodArgs()
        initProgressBarAndTextViewPercent()
        changeFoodGramAmount()
        setBackgroundColorToButton()
        addFoodInDb()
    }

    private fun setFoodName() {
        binding.foodName.text = arg.foodName
    }

    private fun initProgressBarAndTextViewPercent() {
        with(binding) {
            progressProtein.progress = findPercent(PROTEIN, arg.protein)
            proteinPercent.text = String.format(
                getString(R.string.food_detail_percent),
                findPercent(PROTEIN, arg.protein).roundToInt().toString()
            )

            progressFat.progress = findPercent(FAT, arg.fat)
            fatPercent.text = String.format(
                getString(
                    R.string.food_detail_percent
                ),
                findPercent(FAT, arg.fat).roundToInt().toString()
            )

            progressCarbs.progress = findPercent(CARBS, arg.carbs)
            carbsPercent.text = String.format(
                getString(R.string.food_detail_percent),
                findPercent(CARBS, arg.carbs).roundToInt().toString()
            )
        }
    }

    private fun findPercent(nutrition: CaloriesInNutrition, value: Float): Float {
        return when (nutrition) {
            PROTEIN -> (((value * 4) / arg.calories) * 100)
            FAT -> (((value * 9) / arg.calories) * 100)
            CARBS -> (((value * 4) / arg.calories) * 100)
        }
    }

    private fun changeFoodGramAmount() {
        with(binding) {
            //set default value to editText
            editText.setText("100")

            editText.doOnTextChanged { text, _, _, _ ->
                if (text!!.isNotEmpty()) {
                    caloriesAmount.text = String.format(
                        getString(R.string.food_detail_calories_amount),
                        findNewCaloriesAmount(text.toString().toInt())
                    )
                    proteinAmount.text = String.format(
                        getString(R.string.food_detail_gram),
                        DecimalFormat(FLOAT_FORMAT).format(findNewVFloatValue(arg.protein, text.toString().toInt()))
                    )
                    fatAmount.text = String.format(
                        getString(R.string.food_detail_gram),
                        DecimalFormat(FLOAT_FORMAT).format(findNewVFloatValue(arg.fat, text.toString().toInt()))
                    )
                    carbsAmount.text = String.format(
                        getString(R.string.food_detail_gram),
                        DecimalFormat(FLOAT_FORMAT).format(findNewVFloatValue(arg.carbs, text.toString().toInt()))
                    )
                } else {
                    caloriesAmount.text = String.format(getString(R.string.food_detail_calories_amount), "0")
                    proteinAmount.text = String.format(getString(R.string.food_detail_gram), "0.0")
                    fatAmount.text = String.format(getString(R.string.food_detail_gram), "0.0")
                    carbsAmount.text = String.format(getString(R.string.food_detail_gram), "0.0")
                }
            }
        }
    }

    private fun findNewVFloatValue(previousValue: Float, multiplier: Int): Float {
        return ((previousValue * multiplier) / 100)
    }

    private fun findNewCaloriesAmount(multiplier: Int): Int {
        return ((arg.calories * multiplier) / 100)
    }

    private fun setFoodArgs() {
        with(binding) {
            foodName.text = arg.foodName
            caloriesAmount.text =
                String.format(getString(R.string.food_detail_calories_amount), arg.calories.toString())
            proteinAmount.text = String.format(getString(R.string.food_detail_gram), arg.protein.toString())
            fatAmount.text = String.format(getString(R.string.food_detail_gram), arg.fat.toString())
            carbsAmount.text = String.format(getString(R.string.food_detail_gram), arg.carbs.toString())
        }
    }

    private fun setBackgroundColorToButton() {
        binding.addFoodButton.setBackgroundColor(getColor(requireContext(), R.color.light_green))
    }

    private fun addFoodInDb() {
        binding.addFoodButton.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class CaloriesInNutrition {
        PROTEIN, FAT, CARBS
    }

    companion object {
        private const val FLOAT_FORMAT = "0.0"
    }
}