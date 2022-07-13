package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayoutMediator
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentViewpagerContainerBinding
import ilya.chistousov.countcalories.presentation.foods.fragments.BaseFragment
import ilya.chistousov.countcalories.presentation.register.adapter.ViewPagerAdapter
import ilya.chistousov.countcalories.presentation.register.viewmodel.ProfileViewModel

class ViewPagerContainer : BaseFragment<FragmentViewpagerContainerBinding>(
    FragmentViewpagerContainerBinding::inflate
) {

    private lateinit var adapter: ViewPagerAdapter

    private val screenList = mutableListOf(
        GoalScreen(),
        ActivityLevelScreen(),
        GenderScreen(),
        BirthDateScreen(),
        CurrentGrowthScreen(),
        CurrentWeightScreen(),
        DesiredWeightScreen(),
        EmailScreen()
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewPager()
        backToPreviousScreen()
    }


    private fun setUpViewPager() {
        adapter = ViewPagerAdapter(screenList, childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabsIndicator, binding.viewPager) { _, _ ->
        }.attach()
    }


    private fun backToPreviousScreen() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.viewPager.currentItem != 0) {
                    binding.viewPager.currentItem = binding.viewPager.currentItem - 1
                } else {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })
    }

}