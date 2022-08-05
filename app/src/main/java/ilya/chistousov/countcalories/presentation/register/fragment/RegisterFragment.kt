package ilya.chistousov.countcalories.presentation.register.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentRegisterBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.register.viewmodel.AccountViewModel
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var createProfileViewModel: CreateProfileViewModel

    override fun onAttach(context: Context) {
        accountViewModel = context.appComponent.factory.create(AccountViewModel::class.java)
        createProfileViewModel = context.appComponent.factory.create(CreateProfileViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        backToPreviousScreen()
        finishRegistration()
        emailFocusListener()
    }

    private fun finishRegistration() {
        binding.buttonFinishRegister.setOnClickListener {
            if (binding.emailLayout.helperText == null) {
                registerAccount()
                createProfile()
            }
        }
    }

    private fun registerAccount() {
        accountViewModel.signUpWithEmailAndPassword(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString(),
            onFailure = {
                findNavController().navigate(
                    R.id.action_registerFragment_to_errorSignUpDialogFragment)
            }, onSuccess = {
                createProfile()
                findNavController().navigate(R.id.action_registerFragment_to_tabsFragment)
            })
    }


    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailLayout.helperText = validateEmail()
            }
        }
    }

    private fun validateEmail(): String? {
        val email = binding.emailEditText.text.toString()
        if (!email.matches(Patterns.EMAIL_ADDRESS.toRegex())) {
            return getString(R.string.invalid_email_input)
        }
        return null
    }

    private fun createProfile() {
        createProfileViewModel.createProfile()
    }

    private fun backToPreviousScreen() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
