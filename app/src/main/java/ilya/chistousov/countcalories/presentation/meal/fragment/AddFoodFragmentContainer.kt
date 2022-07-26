package ilya.chistousov.countcalories.presentation.meal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import ilya.chistousov.countcalories.databinding.FragmentAddFoodContainerBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.meal.adapter.FoodFragmentAdapter
import ilya.chistousov.countcalories.presentation.meal.screen.AddCustomFoodFragment
import ilya.chistousov.countcalories.presentation.meal.screen.FavoriteFoodFragment
import ilya.chistousov.countcalories.presentation.meal.screen.SearchFoodFragment

class AddFoodFragmentContainer : BaseFragment<FragmentAddFoodContainerBinding>(
    FragmentAddFoodContainerBinding::inflate
) {

    private val foodFragmentAdapter by lazy {
        FoodFragmentAdapter(fragments, childFragmentManager, lifecycle)
    }
    private val fragments = arrayOf<Fragment>(
        SearchFoodFragment(),
        FavoriteFoodFragment(),
        AddCustomFoodFragment()
    )

    private val tabsName = arrayOf(
        "Поиск еды",
        "Любимая еда",
        "Добавить свою еду"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiate()
    }

    private fun initiate() {
        binding.viewPagerContainer.adapter = foodFragmentAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerContainer) { tab, position ->
            tab.text = tabsName[position]

        }.attach()
    }
}