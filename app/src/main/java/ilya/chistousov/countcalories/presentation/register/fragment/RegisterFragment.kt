package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ilya.chistousov.countcalories.databinding.FragmentRegisterBinding
import ilya.chistousov.countcalories.presentation.foods.fragments.BaseFragment
import ilya.chistousov.countcalories.presentation.register.viewmodel.ProfileViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[ProfileViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
