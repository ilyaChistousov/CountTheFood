package ilya.chistousov.countthefood.food.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun Fragment.findTopNavController(fragmentContainerId: Int) : NavController {
    val topNavHostFragment =
        requireActivity().supportFragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment
    return topNavHostFragment.navController
}