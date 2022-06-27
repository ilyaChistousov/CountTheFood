package ilya.chistousov.countcalories.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDiaryBinding

class MealDetailFragment : Fragment(R.layout.fragment_meal_detail) {

    private lateinit var binding: FragmentDiaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiaryBinding.bind(view)
    }


}