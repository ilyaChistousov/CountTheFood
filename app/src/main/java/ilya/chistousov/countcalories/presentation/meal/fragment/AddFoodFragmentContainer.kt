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
        binding.viewPagerContainer.adapter = foodFragmentAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerContainer) { tab, position ->
            tab.text = tabsName[position]
        }.attach()


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon?.alpha = 255
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon?.alpha = 50
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                tab.icon?.alpha = 255
            }

        })
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