package ilya.chistousov.countcalories.presentation.meal.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentSearchFoodBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.meal.adapter.FoodAdapter
import ilya.chistousov.countcalories.presentation.meal.adapter.RecyclerViewOnItemClickListener
import ilya.chistousov.countcalories.presentation.meal.fragment.AddFoodFragmentContainerDirections
import ilya.chistousov.countcalories.presentation.meal.viewmodel.SearchFoodViewModel
import ilya.chistousov.countcalories.util.Resource

class SearchFoodFragment : BaseFragment<FragmentSearchFoodBinding>(
    FragmentSearchFoodBinding::inflate
), RecyclerViewOnItemClickListener {

    private val foodAdapter by lazy {
        FoodAdapter(this)
    }
    private lateinit var searchFoodViewModel: SearchFoodViewModel

    override fun onAttach(context: Context) {
        searchFoodViewModel = context.appComponent.factory.create(SearchFoodViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSearchedFood()
        setSeparatorItemRecycler()
    }

    private fun showSearchedFood() {
        binding.searchFoodEditText.setOnFocusChangeListener { _, focused ->
            with(binding) {
                if (!focused && searchFoodEditText.text != null) {
                    searchFoodViewModel.searchFoodByName(searchFoodEditText.text.toString())
                    searchFoodViewModel.foodFromApi.observe(viewLifecycleOwner) { resource ->
                        when(resource) {
                            is Resource.Loading -> progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                updateUi(resource.data!!)
                            }
                            is Resource.Error -> {
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

    private fun setSeparatorItemRecycler() {
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onItemClick(position: Int) {
        val currentItem = foodAdapter.currentList[position]
        val direction =
            AddFoodFragmentContainerDirections.actionAddFoodFragmentContainerToFoodDetailDialogFragment(
                currentItem.name, currentItem.calories, currentItem.protein, currentItem.fat, currentItem.carbs
            )
        findNavController().navigate(direction)
    }
}