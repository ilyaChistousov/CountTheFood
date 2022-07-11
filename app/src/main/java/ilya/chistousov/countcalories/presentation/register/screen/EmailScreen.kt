package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentEmailBinding


class EmailScreen : BaseScreen<FragmentEmailBinding>(
    FragmentEmailBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showPreviousScreen()
        inputEmail()
    }

    private fun inputEmail() {
        binding.cardViewInputEmail.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerContainer_to_registerFragment)
        }
    }


    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            parentBinding.viewPager.currentItem = 6
        }
    }
}