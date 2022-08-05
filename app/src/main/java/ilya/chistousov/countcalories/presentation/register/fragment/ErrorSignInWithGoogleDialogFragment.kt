package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentErrorSignInWithGoogleDialogBinding

class ErrorSignInWithGoogleDialogFragment : DialogFragment(R.layout.fragment_error_sign_in_with_google_dialog){

    private var binding: FragmentErrorSignInWithGoogleDialogBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDialog()
        binding = FragmentErrorSignInWithGoogleDialogBinding.bind(view)
        retry()
        register()
    }

    private fun setupDialog() {
        dialog?.window?.setLayout(WindowManager.LayoutParams(250).width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun retry() {
        binding!!.retryButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun register() {
        binding!!.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_errorSignInWithGoogleDialogFragment_to_registerFragmentContainer)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}