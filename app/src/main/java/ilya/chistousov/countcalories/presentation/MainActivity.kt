package ilya.chistousov.countcalories.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment
        navController = navHost.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.diaryFragment, R.id.waterFragment)
        )

        NavigationUI.setupWithNavController(binding.bottomNavViewTabs, navController)
    }

}