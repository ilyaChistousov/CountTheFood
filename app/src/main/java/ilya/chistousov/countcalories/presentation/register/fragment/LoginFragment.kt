package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentLoginBinding
import ilya.chistousov.countcalories.presentation.foods.fragments.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLogin.setOnClickListener {
            openRegisterFragment()
        }
    }

    private fun openRegisterFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_viewPagerContainer)
    }
}