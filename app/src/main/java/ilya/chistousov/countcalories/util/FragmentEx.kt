package ilya.chistousov.countcalories.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ilya.chistousov.countcalories.R

fun Fragment.findTopNavController(): NavController {
    val topLevelHost =
        requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
    return topLevelHost.navController
}