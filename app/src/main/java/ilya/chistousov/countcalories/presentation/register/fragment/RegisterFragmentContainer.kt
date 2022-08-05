package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.databinding.FragmentRegisterContainerBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.register.adapter.RegisterAdapter
import ilya.chistousov.countcalories.presentation.register.screen.*

class RegisterFragmentContainer : BaseFragment<FragmentRegisterContainerBinding>(
    FragmentRegisterContainerBinding::inflate
) {

    private val screenList = mutableListOf(
        GoalScreen(),
        ActivityLevelScreen(),
        GenderScreen(),
        BirthDateScreen(),
        ProfileInfoScreen(),
        EmailScreen()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewPager()
        backToPreviousScreen()
    }


    private fun setUpViewPager() {
        val adapter = RegisterAdapter(screenList, childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    private fun backToPreviousScreen() {
        with(binding) {
            toolBar.setNavigationOnClickListener {
                if (viewPager.currentItem == FIRST_SCREEN) {
                    findNavController().popBackStack()
                }
                viewPager.currentItem = viewPager.currentItem - 1
            }

            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (viewPager.currentItem != FIRST_SCREEN) {
                            viewPager.currentItem = viewPager.currentItem - 1
                        } else {
                            isEnabled = false
                            requireActivity().onBackPressed()
                        }
                    }
                }
            )
        }
    }

    companion object {
        const val FIRST_SCREEN = 0
        const val SECOND_SCREEN = 1
        const val THIRD_SCREEN = 2
        const val FOURTH_SCREEN = 3
        const val FIFTH_SCREEN = 4
        const val SIXTH_SCREEN = 5
    }
}