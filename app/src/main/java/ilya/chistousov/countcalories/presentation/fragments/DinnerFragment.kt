package ilya.chistousov.countcalories.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentDinnerBinding

class DinnerFragment : Fragment(R.layout.fragment_dinner) {

    private lateinit var binding: FragmentDinnerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDinnerBinding.bind(view)
    }


}