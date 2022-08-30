package ilya.chistousov.countthefood.food.presentation.meal.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.core.model.Food
import ilya.chistousov.countthefood.core.result.Result
import ilya.chistousov.countthefood.food.di.FoodComponentViewModel
import ilya.chistousov.countthefood.food.presentation.meal.adapter.FoodAdapter
import ilya.chistousov.countthefood.food.presentation.meal.adapter.RecyclerViewOnItemClickListener
import ilya.chistousov.countthefood.food.presentation.meal.fragment.AddFoodFragmentContainer
import ilya.chistousov.countthefood.food.presentation.meal.fragment.AddFoodFragmentContainerDirections
import ilya.chistousov.countthefood.food.presentation.meal.viewmodel.SearchFoodViewModel
import ilya.chistousov.food.databinding.FragmentSearchFoodBinding

class SearchFoodFragment : BaseFragment<FragmentSearchFoodBinding>(
    FragmentSearchFoodBinding::inflate
), RecyclerViewOnItemClickListener {

    private val foodAdapter by lazy {
        FoodAdapter(this)
    }
    private lateinit var searchFoodViewModel: SearchFoodViewModel

    override fun onAttach(context: Context) {
        searchFoodViewModel = ViewModelProvider(this)
            .get<FoodComponentViewModel>().foodComponent.factory.create(SearchFoodViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSearchedFood()
    }

    private fun showSearchedFood() {
        with(binding) {
            searchFoodEditText.setOnFocusChangeListener { _, focused ->
                if (!focused && searchFoodEditText.text != null) {
                    searchFoodViewModel.searchFoodByName(searchFoodEditText.text.toString())
                    searchFoodViewModel.foodFromApi.observe(viewLifecycleOwner) { resource ->
                        when (resource) {
                            is Result.Loading -> progressBar.visibility = View.VISIBLE
                            is Result.Success -> {
                                if (resource.data!!.isEmpty()) {
                                    progressBar.visibility = View.GONE
                                    errorTextView.visibility = View.VISIBLE
                                } else {
                                    progressBar.visibility = View.GONE
                                    updateUi(resource.data!!)
                                }
                            }
                            is Result.Error -> {
                                progressBar.visibility = View.GONE
                                errorTextView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateUi(foods: List<Food>) {
        foodAdapter.submitList(foods)
        binding.recyclerView.adapter = foodAdapter
    }

    override fun onItemClick(position: Int) {
        val currentFood = foodAdapter.currentList[position]
        val meal = (parentFragment as AddFoodFragmentContainer).agrs.meal
        val direction =
            AddFoodFragmentContainerDirections.actionAddFoodFragmentContainerToFoodDetailDialogFragment(
                meal, currentFood
            )
        findNavController().navigate(direction)
    }
}