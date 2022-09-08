package ilya.chistousov.countthefood.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ilya.chistousov.countthefood.R
import ilya.chistousov.countthefood.databinding.FragmentTabsBinding
import ilya.chistousov.countthefood.core.basefragment.BaseFragment

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
        binding.bottomNavViewTabs.setBackgroundColor(ContextCompat.getColor(
            requireContext(), ilya.chistousov.countthefood.core.R.color.light_green))
    }

}