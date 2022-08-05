package ilya.chistousov.countcalories.presentation.register.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentSignInWithEmailAndPasswordBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.register.viewmodel.AccountViewModel

class SignInWithEmailAndPasswordFragment : BaseFragment<FragmentSignInWithEmailAndPasswordBinding>(
    FragmentSignInWithEmailAndPasswordBinding::inflate
) {

    private lateinit var accountViewModel: AccountViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountViewModel = context.appComponent.factory.create(AccountViewModel::class.java)
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
                accountViewModel.signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString(),
                    onSuccess = { findNavController().navigate(
                        R.id.action_signInWithEmailAndPasswordFragment_to_tabsFragment) },
                    onFailure = { findNavController().navigate(
                        R.id.action_signInWithEmailAndPasswordFragment_to_errorSignInWithEmailDialogFragment) }
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
            findNavController().navigate(R.id.action_signInWithEmailAndPasswordFragment_to_resetPasswordFragment)
        }
    }
}