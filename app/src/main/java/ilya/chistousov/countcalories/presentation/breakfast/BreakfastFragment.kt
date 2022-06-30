package ilya.chistousov.countcalories.presentation.breakfast

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentBreakfastBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.presentation.recyclerview.adapter.FoodAdapter
import ilya.chistousov.countcalories.presentation.viewmodels.FoodViewModel
import kotlinx.coroutines.launch

class BreakfastFragment : Fragment(R.layout.fragment_breakfast) {

    private lateinit var binding: FragmentBreakfastBinding
    private val foodViewModel: FoodViewModel by lazy {
        ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(requireActivity().application))[FoodViewModel::class.java]
    }
    private lateinit var adapter: FoodAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakfastBinding.bind(view)
        adapter = FoodAdapter()
        addNewFood()
        observeListFood()
    }

    private fun addNewFood() {
        binding.buttonAddFood.setOnClickListener {
            val foodName = binding.editText.text.toString()
            val newFood = Food(
                0,
                name = foodName,
                100.0,
                100.0,
                100.0,
                100.0,
                Meal.BREAKFAST)
            foodViewModel.addFood(newFood)
        }
    }

    private fun observeListFood() {
        foodViewModel.getFoodByMeal(Meal.BREAKFAST).observe(viewLifecycleOwner) {
            updateUi(it)
            getCurrentInfo(it)
        }
    }
    private fun updateUi(foods: List<Food>) {
        adapter.setList(foods)
        binding.recyclerView.adapter = adapter
    }


    private fun getCurrentInfo(foodList: List<Food>) {
        var caloriesSum = DEFAULT_VALUE
        var proteinSum = DEFAULT_VALUE
        var fatsSum = DEFAULT_VALUE
        var carbsSum = DEFAULT_VALUE

        for (food in foodList.listIterator()) {
            caloriesSum += food.calories
            proteinSum += food.proteins
            fatsSum += food.fats
            carbsSum += food.carbs
        }

        binding.textViewCaloriesSum.text = caloriesSum.toString()
        binding.textViewProteinsSum.text = proteinSum.toString()
        binding.textViewFatsSum.text = fatsSum.toString()
        binding.textViewCarbsSum.text = carbsSum.toString()

        updateUiInDiaryFragment(caloriesSum, proteinSum, fatsSum, carbsSum)
    }

    private fun updateUiInDiaryFragment(
        calories: Double, protein: Double, fats: Double, carbs: Double
    ) {
        parentFragmentManager.setFragmentResult(REQUEST_CODE,
            bundleOf(
            EXTRA_CALORIES_SUM to calories,
            EXTRA_PROTEINS_SUM to protein,
            EXTRA_FATS_SUM to fats,
            EXTRA_CARBS_SUM to carbs
        ))
    }

    companion object {
        private const val DEFAULT_VALUE = 0.0
        const val REQUEST_CODE = "CURRENT_FOOD_INFO_REQUEST_CODE"
        const val EXTRA_CALORIES_SUM = "CALORIES_SUM"
        const val EXTRA_PROTEINS_SUM = "PROTEINS_SUM"
        const val EXTRA_FATS_SUM = "FATS_SUM"
        const val EXTRA_CARBS_SUM = "CARBS_SUM"
    }
}