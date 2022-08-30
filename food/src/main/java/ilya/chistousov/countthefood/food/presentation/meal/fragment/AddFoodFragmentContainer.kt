package ilya.chistousov.countthefood.food.presentation.meal.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.food.presentation.meal.adapter.FoodFragmentAdapter
import ilya.chistousov.countthefood.food.presentation.meal.screen.SearchFoodFragment
import ilya.chistousov.food.databinding.FragmentAddFoodContainerBinding

class AddFoodFragmentContainer : BaseFragment<FragmentAddFoodContainerBinding>(
    FragmentAddFoodContainerBinding::inflate
) {
    val agrs: AddFoodFragmentContainerArgs by navArgs()

    private val foodFragmentAdapter by lazy {
        FoodFragmentAdapter(fragments, childFragmentManager, lifecycle)
    }

    private val fragments = arrayOf<Fragment>(
        SearchFoodFragment()
    )

    private val tabsName = arrayOf(
        "Поиск продуктов"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTabs()
        setupToolBarTitle()
        popBackStack()
    }

    private fun initTabs() {
        with(binding) {
            viewPagerContainer.adapter = foodFragmentAdapter
            TabLayoutMediator(tabLayout, viewPagerContainer) { tab, position ->
                tab.text = tabsName[position]
            }.attach()
        }
    }

    private fun setupToolBarTitle() {
        binding.toolBar.title = agrs.mealName
    }

    private fun popBackStack() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}