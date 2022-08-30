package ilya.chistousov.countthefood.signup.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.signup.R
import ilya.chistousov.countthefood.signup.databinding.FragmentSignUpBinding
import ilya.chistousov.countthefood.signup.di.SignUpComponentViewModel
import ilya.chistousov.countthefood.signup.presentation.viewmodel.CreateProfileViewModel
import ilya.chistousov.countthefood.signup.presentation.viewmodel.SignUpViewModel

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
) {

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var createProfileViewModel: CreateProfileViewModel

    override fun onAttach(context: Context) {
        val signUpComponent = ViewModelProvider(this).get<SignUpComponentViewModel>().signUpComponent
        signUpViewModel = signUpComponent.factory.create(SignUpViewModel::class.java)
        createProfileViewModel = signUpComponent.factory.create(CreateProfileViewModel::class.java)
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
        signUpViewModel.signUpWithEmailAndPassword(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString(),
            onFailure = {
                findNavController().navigate(
                    ilya.chistousov.countthefood.core.R.id.action_signUpFragment_to_errorSignUpDialogFragment
                )
            }, onSuccess = {
                createProfile()
                findNavController().navigate(ilya.chistousov.countthefood.core.R.id.action_global_tabsFragment)
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
