package ilya.chistousov.countthefood.signin.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.signin.R
import ilya.chistousov.countthefood.signin.databinding.FragmentSignInWithEmailAndPasswordBinding
import ilya.chistousov.countthefood.signin.di.SignInComponentViewModel
import ilya.chistousov.countthefood.signin.presentation.viewmodel.SignInViewModel

class SignInWithEmailAndPasswordFragment : BaseFragment<FragmentSignInWithEmailAndPasswordBinding>(
    FragmentSignInWithEmailAndPasswordBinding::inflate
) {

    private lateinit var signInViewModel: SignInViewModel

    override fun onAttach(context: Context) {
        signInViewModel = ViewModelProvider(this)
            .get<SignInComponentViewModel>()
            .signInComponent.factory.create(SignInViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        emailFocusListener()
        backToPreviousScreen()
        loginAccount()
        resetPassword()
    }

    private fun loginAccount() {
        binding.signInButton.setOnClickListener {
            if (binding.emailLayout.helperText == null) {
                val email = binding.emailEditText.text.toString()
                signInViewModel.signInWithEmailAndPassword(
                    email,
                    binding.passwordEditText.text.toString(),
                    onSuccess = {
                        signInViewModel.createProfile(email)
                        findNavController().navigate(
                        ilya.chistousov.countthefood.core.R.id.action_global_loadingScreen) },
                    onFailure = { findNavController().navigate(
                        ilya.chistousov.countthefood.core.R.id.action_signInWithEmailAndPasswordFragment_to_errorSignInWithEmailDialogFragment2) }
                )
            }
        }
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

    private fun backToPreviousScreen() {
        binding.back.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun resetPassword() {
        binding.resetPasswordText.setOnClickListener {
            findNavController().navigate(ilya.chistousov.countthefood.core.R.id.action_signInWithEmailAndPasswordFragment_to_resetPasswordFragment)
        }
    }
}