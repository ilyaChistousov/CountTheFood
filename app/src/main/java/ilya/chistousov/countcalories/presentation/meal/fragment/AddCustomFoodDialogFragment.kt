package ilya.chistousov.countcalories.presentation.meal.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentAddCustomFoodDialogBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.presentation.meal.viewmodel.AddFoodViewModel
import java.time.LocalDate

class AddCustomFoodDialogFragment : DialogFragment(R.layout.fragment_add_custom_food_dialog) {

    private var _binding: FragmentAddCustomFoodDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddFoodViewModel

    override fun onAttach(context: Context) {
        viewModel = context.appComponent.factory.create(AddFoodViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentAddCustomFoodDialogBinding.bind(view)
        setupDialog()
        addCustomFood()
        exitDialog()
    }

    private fun setupDialog() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun addCustomFood() {
        with(binding) {
            validateInputData()

            addFoodButton.setOnClickListener {
                if (checkValidation()) {
                    val customFood = Food(
                        name = editTextFoodName.text.toString(),
                        calories = caloriesEditText.text.toString().toInt(),
                        protein = proteinEditText.text.toString().toFloat(),
                        fat = fatEditText.text.toString().toFloat(),
                        carbs = carbsEditText.text.toString().toFloat(),
                        meal = Meal.NONE,
                        addedDate = LocalDate.now(),
                        isCustom = true
                    )
                    viewModel.addFood(customFood)
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun validateInputData() {
        with(binding) {
            foodNameLayout.error = getString(R.string.error_input)
            caloriesLayout.error = getString(R.string.error_input)
            proteinLayout.error = getString(R.string.error_input)
            fatLayout.error = getString(R.string.error_input)
            carbsLayout.error = getString(R.string.error_input)

            validateInFocus(foodNameLayout, editTextFoodName)
            validateInFocus(caloriesLayout, caloriesEditText)
            validateInFocus(proteinLayout, proteinEditText)
            validateInFocus(fatLayout, fatEditText)
            validateInFocus(carbsLayout, carbsEditText)
        }
    }

    private fun validateInFocus(textLayout: TextInputLayout, editText: TextInputEditText) {
        editText.setOnFocusChangeListener { _, isFocused ->
            if (isFocused || (!isFocused && editText.text!!.isNotEmpty())) {
                textLayout.error = null
            } else {
                textLayout.error = getString(R.string.error_input)
            }
        }
    }

    private fun checkValidation() : Boolean {
        with(binding) {
             if (foodNameLayout.error == null && caloriesLayout.error == null &&
                 proteinLayout.error == null && fatLayout.error == null && carbsLayout.error == null) {
                return true
            }
            return false
        }
    }

    private fun exitDialog() {
        binding.imageButtonExit.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}