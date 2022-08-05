package ilya.chistousov.countcalories.presentation.register.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentResetPasswordBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment
import ilya.chistousov.countcalories.presentation.register.viewmodel.AccountViewModel

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>(
    FragmentResetPasswordBinding::inflate
) {

    private lateinit var accountViewModel: AccountViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountViewModel = context.appComponent.factory.create(AccountViewModel::class.java)
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
                accountViewModel.resetPassword(binding.emailEditText.text.toString(),
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