package ilya.chistousov.countcalories.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentSnackBinding

class SnackFragment : Fragment(R.layout.fragment_snack) {

    private lateinit var binding: FragmentSnackBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSnackBinding.bind(view)
    }
}