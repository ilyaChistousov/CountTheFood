package ilya.chistousov.countcalories.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindBottomViewWithNavComponent()
    }


    private fun bindBottomViewWithNavComponent() {
        val navHost = supportFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment
        val navController = navHost.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavViewTabs, navController)
    }

}