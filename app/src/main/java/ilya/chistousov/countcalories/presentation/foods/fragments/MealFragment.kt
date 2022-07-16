package ilya.chistousov.countcalories.presentation.foods.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentMealBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.presentation.foods.recyclerview.adapter.FoodAdapter
import ilya.chistousov.countcalories.presentation.util.filterListFoodByMealAndDate
import ilya.chistousov.countcalories.presentation.foods.viewmodels.DiaryViewModel
import ilya.chistousov.countcalories.presentation.foods.viewmodels.MealViewModel
import ilya.chistousov.countcalories.presentation.foods.viewmodels.MealViewModelFactory
import javax.inject.Inject

open class MealFragment : BaseFragment<FragmentMealBinding>(
    FragmentMealBinding::inflate
) {

    private val mealViewModel: MealViewModel by viewModels() {
        mealFactory.create()
    }

    private val args: MealFragmentArgs by navArgs()

    @Inject
    lateinit var mealFactory: MealViewModelFactory.Factory

    private val adapter by lazy {
        FoodAdapter()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewFood()
        getAllFood()
    }

    private fun addNewFood() {
        binding.buttonAddFood.setOnClickListener {
            val foodName = binding.editText.text.toString()
            val newFood = Food(
                name = foodName,
                calories = 100,
                proteins = 100.0f,
                fats = 100.0f,
                carbs = 100.0f,
                meal = args.meal,
                addedDate = args.addedDate
            )
            mealViewModel.addFood(newFood)
        }
    }

    private fun getAllFood() {
        mealViewModel.foods.observe(viewLifecycleOwner) {
            val filteredListByMeal = it.filterListFoodByMealAndDate(args.meal, args.addedDate)
            updateUi(filteredListByMeal)
            getCurrentInfo(filteredListByMeal)
        }
    }

    private fun updateUi(foods: List<Food>) {
        adapter.submitList(foods)
        binding.recyclerView.adapter = adapter
    }


    private fun getCurrentInfo(foodList: List<Food>) {
        var caloriesSum = DEFAULT_VALUE
        var proteinSum = DEFAULT_VALUE
        var fatsSum = DEFAULT_VALUE
        var carbsSum = DEFAULT_VALUE

        foodList.forEach {
            caloriesSum += it.calories
            proteinSum += it.proteins.toInt()
            fatsSum = +it.fats.toInt()
            carbsSum = +it.carbs.toInt()
        }

        binding.textViewCaloriesSum.text = caloriesSum.toString()
        binding.textViewProteinsSum.text = proteinSum.toString()
        binding.textViewFatsSum.text = fatsSum.toString()
        binding.textViewCarbsSum.text = carbsSum.toString()

    }

    companion object {
        const val DEFAULT_VALUE = 0
    }
}