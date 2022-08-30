package ilya.chistousov.countthefood.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import ilya.chistousov.countthefood.App
import ilya.chistousov.countthefood.R
import ilya.chistousov.countthefood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding

    private var firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setNavigationGraph()
    }

    private fun setNavigationGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(ilya.chistousov.countthefood.core.R.navigation.main_graph)

        val currentUser = firebaseAuth.currentUser
        navGraph.setStartDestination(
            if (currentUser == null) {
                ilya.chistousov.countthefood.core.R.id.welcomeFragment
            } else {
                ilya.chistousov.countthefood.core.R.id.tabsFragment
            }
        )
//        navGraph.setStartDestination(R.id.tabsFragment)
        navController.graph = navGraph
    }
}