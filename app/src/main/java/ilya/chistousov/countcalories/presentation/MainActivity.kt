package ilya.chistousov.countcalories.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.ActivityMainBinding
import ilya.chistousov.countcalories.presentation.fragments.TabsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    private fun getTabsDestination() = R.id.tabsFragment
}