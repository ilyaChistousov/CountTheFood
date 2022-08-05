package ilya.chistousov.countcalories.presentation.meal.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ilya.chistousov.countcalories.databinding.FragmentAddFoodContainerBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.meal.adapter.FoodFragmentAdapter
import ilya.chistousov.countcalories.presentation.meal.screen.AddCustomFoodFragment
import ilya.chistousov.countcalories.presentation.meal.screen.SearchFoodFragment

class AddFoodFragmentContainer : BaseFragment<FragmentAddFoodContainerBinding>(
    FragmentAddFoodContainerBinding::inflate
) {
    val args: AddFoodFragmentContainerArgs by navArgs()

    private val foodFragmentAdapter by lazy {
        FoodFragmentAdapter(fragments, childFragmentManager, lifecycle)
    }

    private val fragments = arrayOf<Fragment>(
        SearchFoodFragment(),
        AddCustomFoodFragment()
    )

    private val tabsName = arrayOf(
        "Поиск продуктов",
        "Добавить свой продукт"
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
        binding.toolBar.title = args.mealName
    }

    private fun popBackStack() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}