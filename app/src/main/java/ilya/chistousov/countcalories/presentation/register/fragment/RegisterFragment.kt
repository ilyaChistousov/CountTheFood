package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentRegisterBinding
import ilya.chistousov.countcalories.presentation.foods.fragments.BaseFragment
import ilya.chistousov.countcalories.presentation.register.viewmodel.ProfileViewModel
import ilya.chistousov.countcalories.presentation.register.viewmodel.RegisterAccountViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[ProfileViewModel::class.java]
    }

    private val registerAccountViewModel: RegisterAccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showPreviousScreen()
        finishRegistration()
        emailFocusListener()
    }

    private fun finishRegistration() {
        binding.buttonFinishRegister.setOnClickListener {
            if(binding.emailLayout.helperText == null) {
                registerAccount()
//                createProfile()
                findNavController().navigate(R.id.action_registerFragment_to_tabsFragment)
            }
        }
    }

    private fun registerAccount() {
        registerAccountViewModel.registerAccount(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString()
        )
    }

    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.emailLayout.helperText = validateEmail()
            }
        }
    }

    private fun validateEmail() : String? {
        val email = binding.emailEditText.text.toString()
        if(!email.matches(Patterns.EMAIL_ADDRESS.toRegex())) {
            return getString(R.string.invalid_email_input)
        }
        return null
    }

    private fun createProfile() {
        profileViewModel.createProfile()
    }

    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
