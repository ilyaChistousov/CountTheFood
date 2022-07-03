package ilya.chistousov.countcalories.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentBaseMealBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.domain.model.Meal
import ilya.chistousov.countcalories.presentation.recyclerview.adapter.FoodAdapter
import ilya.chistousov.countcalories.presentation.util.filterListFoodByMeal
import ilya.chistousov.countcalories.presentation.viewmodels.FoodViewModel

abstract class BaseMealFragment : Fragment(R.layout.fragment_base_meal) {

    private val foodViewModel: FoodViewModel by lazy {
        ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(requireActivity().application))[FoodViewModel::class.java]
    }
    private lateinit var adapter: FoodAdapter
    private lateinit var binding: FragmentBaseMealBinding
    abstract val meal: Meal


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBaseMealBinding.bind(view)
        adapter = FoodAdapter()
        addNewFood()
        observeListFood()
    }

    private fun addNewFood() {
        binding.buttonAddFood.setOnClickListener {
            val foodName = binding.editText.text.toString()
            val newFood = Food(
                name = foodName,
                calories = 100,
                proteins = 100.0,
                fats = 100.0,
                carbs = 100.0,
                meal = this.meal)
            foodViewModel.addFood(newFood)
        }
    }

    private fun observeListFood() {
        foodViewModel.foods.observe(viewLifecycleOwner) {
            val filteredListByMeal = it.filterListFoodByMeal(this.meal)
            updateUi(filteredListByMeal)
            getCurrentInfo(filteredListByMeal)
        }
    }

    private fun updateUi(foods: List<Food>) {
        adapter.submitList(foods)
        binding.recyclerView.adapter = adapter
    }


    private fun getCurrentInfo(foodList: List<Food>) {
        var caloriesSum = DEFAULT_VALUE.toInt()
        var proteinSum = DEFAULT_VALUE
        var fatsSum = DEFAULT_VALUE
        var carbsSum = DEFAULT_VALUE

        foodList.forEach {
            caloriesSum += it.calories
            proteinSum += it.proteins
            fatsSum =+ it.fats
            carbsSum =+ it.carbs
        }

        binding.textViewCaloriesSum.text = caloriesSum.toString()
        binding.textViewProteinsSum.text = proteinSum.toString()
        binding.textViewFatsSum.text = fatsSum.toString()
        binding.textViewCarbsSum.text = carbsSum.toString()

    }


    companion object {
        const val DEFAULT_VALUE = 0.0
    }
}