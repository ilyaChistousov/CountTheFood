package ilya.chistousov.countthefood.food.presentation.meal.fragment

import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.food.di.FoodComponentViewModel
import ilya.chistousov.countthefood.food.presentation.meal.adapter.FoodAdapter
import ilya.chistousov.countthefood.food.presentation.meal.adapter.RecyclerViewOnItemClickListener
import ilya.chistousov.countthefood.food.presentation.meal.viewmodel.MealViewModel
import ilya.chistousov.countthefood.food.util.DEFAULT_FLOAT_VALUE
import ilya.chistousov.countthefood.food.util.DEFAULT_INT_VALUE
import ilya.chistousov.countthefood.food.util.FLOAT_FORMAT
import ilya.chistousov.countthefood.food.util.filterListFoodByMealAndDate
import ilya.chistousov.food.R
import ilya.chistousov.food.databinding.FragmentMealBinding
import java.text.DecimalFormat

class MealFragment : BaseFragment<FragmentMealBinding>(
    FragmentMealBinding::inflate
), RecyclerViewOnItemClickListener {

    private val args: MealFragmentArgs by navArgs()

    private lateinit var mealViewModel: MealViewModel

    private val adapter by lazy {
        FoodAdapter(this)
    }

    override fun onAttach(context: Context) {
        mealViewModel = ViewModelProvider(this)
            .get<FoodComponentViewModel>().foodComponent.factory.create(MealViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleTopBarAndIcon()
        getAllFood()
        navigateToSearchFood()
        backToDiaryFragment()
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
        var caloriesSum = DEFAULT_INT_VALUE
        var proteinSum = DEFAULT_FLOAT_VALUE
        var fatsSum = DEFAULT_FLOAT_VALUE
        var carbsSum = DEFAULT_FLOAT_VALUE

        foodList.forEach {
            caloriesSum += it.calories
            proteinSum += it.protein
            fatsSum = +it.fat
            carbsSum = +it.carbs
        }
        with(binding) {
            caloriesAmount.text = String.format(getString(R.string.food_detail_calories_amount), caloriesSum)
            proteinAmount.text = String.format(
                getString(R.string.food_detail_gram),
                DecimalFormat(FLOAT_FORMAT).format(proteinSum)
            )
            fatAmount.text = String.format(
                getString(R.string.food_detail_gram), DecimalFormat(FLOAT_FORMAT).format(fatsSum)
            )
            carbsAmount.text = String.format(
                getString(R.string.food_detail_gram), DecimalFormat(FLOAT_FORMAT).format(carbsSum)
            )
        }
    }

    private fun navigateToSearchFood() {
        binding.buttonAddFood.setOnClickListener {
            val directions = MealFragmentDirections.actionMealFragmentToAddFoodFragmentContainer(args.meal, args.mealName)
            findNavController().navigate(directions)
        }
    }

    private fun backToDiaryFragment() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setTitleTopBarAndIcon() {
        binding.toolBar.title = args.mealName
        binding.imageMeal.setImageIcon(Icon.createWithResource(requireContext(), args.mealIconId))
    }

    override fun onItemClick(position: Int) {
        val currentFood = adapter.currentList[position]
        val directions = MealFragmentDirections.actionMealFragmentToFoodDetailDialogFragment(
            args.meal, currentFood
        )
        findNavController().navigate(directions)
    }
}