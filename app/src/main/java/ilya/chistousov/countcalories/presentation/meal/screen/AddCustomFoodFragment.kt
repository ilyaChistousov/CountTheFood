package ilya.chistousov.countcalories.presentation.meal.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentAddCustomFoodBinding
import ilya.chistousov.countcalories.domain.model.Food
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.meal.adapter.FoodAdapter
import ilya.chistousov.countcalories.presentation.meal.adapter.RecyclerViewOnItemClickListener
import ilya.chistousov.countcalories.presentation.meal.fragment.AddFoodFragmentContainer
import ilya.chistousov.countcalories.presentation.meal.fragment.AddFoodFragmentContainerDirections
import ilya.chistousov.countcalories.presentation.meal.viewmodel.AddFoodViewModel

class AddCustomFoodFragment : BaseFragment<FragmentAddCustomFoodBinding>(
    FragmentAddCustomFoodBinding::inflate
), RecyclerViewOnItemClickListener {

    private lateinit var viewModel: AddFoodViewModel

    private val adapter by lazy {
        FoodAdapter(this)
    }

    override fun onAttach(context: Context) {
        viewModel = context.appComponent.factory.create(AddFoodViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showAllCustomFood()
        addCustomFood()
    }

    private fun showAllCustomFood() {
        viewModel.customFoods.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.textIntro.visibility = View.VISIBLE
            } else {
                binding.textIntro.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                updateUi(it)
            }

        }
    }

    private fun updateUi(foods: List<Food>) {
        adapter.submitList(foods)
        binding.recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val meal = (parentFragment as AddFoodFragmentContainer).args.meal
        val currentFood = adapter.currentList[position]
        val direction = AddFoodFragmentContainerDirections.actionAddFoodFragmentContainerToFoodDetailDialogFragment(
            meal,
            currentFood
        )
        findNavController().navigate(direction)
    }

    private fun addCustomFood() {
        binding.addFoodButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFoodFragmentContainer_to_addCustomFoodDialogFragment)
        }
    }
}