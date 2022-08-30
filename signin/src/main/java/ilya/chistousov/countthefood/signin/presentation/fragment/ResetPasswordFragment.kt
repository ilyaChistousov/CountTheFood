package ilya.chistousov.countthefood.signin.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.signin.databinding.FragmentResetPasswordBinding
import ilya.chistousov.countthefood.signin.di.SignInComponentViewModel
import ilya.chistousov.countthefood.signin.presentation.viewmodel.SignInViewModel

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>(
    FragmentResetPasswordBinding::inflate
) {

    private lateinit var signInViewModel: SignInViewModel

    override fun onAttach(context: Context) {
        signInViewModel = ViewModelProvider(this)
            .get<SignInComponentViewModel>()
            .signInComponent.factory.create(SignInViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        backToPreviousScreen()
        resetPassword()
    }

    private fun backToPreviousScreen() {
        binding.back.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun validateEmail() : Boolean {
        val text = binding.emailEditText.text.toString().trim()
        if (text.isEmpty() || !Patterns.EMAIL_ADDRESS.toRegex().matches(text)) {
            makeToast("Введен неккоректный адрес электронной почты")
            return false
        }
        return true
    }

    private fun resetPassword() {
        binding.resetPasswordButton.setOnClickListener {
            if (validateEmail()) {
                signInViewModel.resetPassword(binding.emailEditText.text.toString(),
                onSuccess = { makeToast("Ссылка для изменения пароля была отправлена на указанную почту") },
                onFailure = { makeToast("Введенный адрес электронной почты не зарегистрирован")})
            }
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(
            requireActivity(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}