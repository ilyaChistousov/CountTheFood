package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentErrorSignUpDialogBinding

class ErrorSignInWithEmailDialogFragment : DialogFragment(R.layout.fragment_error_sign_in_with_email_dialog) {

    private var binding: FragmentErrorSignUpDialogBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialog()
        binding = FragmentErrorSignUpDialogBinding.bind(view)
        retry()
    }

    private fun setupDialog() {
        dialog?.window?.setLayout(WindowManager.LayoutParams(250).width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun retry() {
        binding!!.retryButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}