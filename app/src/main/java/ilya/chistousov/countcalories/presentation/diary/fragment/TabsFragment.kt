package ilya.chistousov.countcalories.presentation.diary.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentTabsBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment

class TabsFragment : BaseFragment<FragmentTabsBinding>(
    FragmentTabsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindBottomNavWithNavComponent()
        setBackgroundColorBottomNav()
    }

    private fun bindBottomNavWithNavComponent() {
        val navHost = childFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment
        val navController = navHost.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavViewTabs, navController)
    }

    private fun setBackgroundColorBottomNav() {
        binding.bottomNavViewTabs.setBackgroundColor(resources.getColor(R.color.light_green))
    }
}