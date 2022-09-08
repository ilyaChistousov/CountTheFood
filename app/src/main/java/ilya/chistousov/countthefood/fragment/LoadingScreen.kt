package ilya.chistousov.countthefood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countthefood.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingScreen : Fragment(R.layout.fragment_loading_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadDataAndGoToTabsFragment()
    }

    private fun loadDataAndGoToTabsFragment() {
        lifecycleScope.launch {
            delay(5000)
            findNavController().navigate(
                ilya.chistousov.countthefood.core.R.id.action_loadingScreen_to_tabsFragment)
        }
    }

}