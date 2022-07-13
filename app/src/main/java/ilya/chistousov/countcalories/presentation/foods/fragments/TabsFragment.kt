package ilya.chistousov.countcalories.presentation.foods.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentTabsBinding

class TabsFragment : BaseFragment<FragmentTabsBinding>(
    FragmentTabsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindBottomViewWithNavComponent()
    }

    private fun bindBottomViewWithNavComponent() {
        val navHost = childFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment
        val navController = navHost.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavViewTabs, navController)
    }
}