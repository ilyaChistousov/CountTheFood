package ilya.chistousov.countcalories.presentation.register.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentRegisterBinding
import ilya.chistousov.countcalories.presentation.foods.fragments.BaseFragment
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModel
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModelFactory
import ilya.chistousov.countcalories.presentation.register.viewmodel.RegisterAccountViewModel
import ilya.chistousov.countcalories.presentation.register.viewmodel.RegisterAccountViewModelFactory
import javax.inject.Inject

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private val registerAccountViewModel: RegisterAccountViewModel by viewModels {
        registerAccountFactory.create()
    }

    private val createProfileViewModel: CreateProfileViewModel by viewModels {
        createProfileFactory.create()
    }

    @Inject
    lateinit var createProfileFactory: CreateProfileViewModelFactory.Factory

    @Inject
    lateinit var registerAccountFactory: RegisterAccountViewModelFactory.Factory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showPreviousScreen()
        finishRegistration()
        emailFocusListener()
    }

    private fun finishRegistration() {
        binding.buttonFinishRegister.setOnClickListener {
            if(binding.emailLayout.helperText == null) {
                registerAccount()
                createProfile()
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
        createProfileViewModel.createProfile()
    }

    private fun showPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
