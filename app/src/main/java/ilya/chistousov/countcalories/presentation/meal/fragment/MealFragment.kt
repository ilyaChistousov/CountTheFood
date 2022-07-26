package ilya.chistousov.countcalories.presentation.meal.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentMealBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.meal.adapter.FoodAdapter
import ilya.chistousov.countcalories.util.filterListFoodByMealAndDate
import ilya.chistousov.countcalories.presentation.foods.viewmodels.MealViewModel
import ilya.chistousov.countcalories.presentation.foods.viewmodels.MealViewModelFactory
import ilya.chistousov.countcalories.presentation.meal.adapter.RecyclerViewOnItemClickListener
import javax.inject.Inject

class MealFragment : BaseFragment<FragmentMealBinding>(
    FragmentMealBinding::inflate
), RecyclerViewOnItemClickListener {

    private val args: MealFragmentArgs by navArgs()

    private lateinit var mealViewModel: MealViewModel

    private val adapter by lazy {
        FoodAdapter(this)
    }

    override fun onAttach(context: Context) {
        mealViewModel = context.appComponent.factory.create(MealViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllFood()
        navigateToSearchFood()
        setSeparatorItemRecycler()
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
            proteinSum += it.protein.toInt()
            fatsSum = +it.fat.toInt()
            carbsSum = +it.carbs.toInt()
        }
    }

    private fun navigateToSearchFood() {
        binding.buttonAddFood.setOnClickListener {
            findNavController().navigate(R.id.action_mealFragment_to_addFoodFragmentContainer)
        }
    }

    private fun setSeparatorItemRecycler() {
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    companion object {
        const val DEFAULT_VALUE = 0
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}