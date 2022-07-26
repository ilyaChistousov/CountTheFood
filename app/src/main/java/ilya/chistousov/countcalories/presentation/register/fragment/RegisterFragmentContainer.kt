package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import ilya.chistousov.countcalories.databinding.FragmentRegisterContainerBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.register.adapter.RegisterAdapter
import ilya.chistousov.countcalories.presentation.register.screen.*

class RegisterFragmentContainer : BaseFragment<FragmentRegisterContainerBinding>(
    FragmentRegisterContainerBinding::inflate
) {

    private val adapter by lazy {
        RegisterAdapter(screenList, childFragmentManager, lifecycle)
    }

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

    private fun setBackgroundPage() {
    }
}