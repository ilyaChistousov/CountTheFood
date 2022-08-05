package ilya.chistousov.countcalories.presentation.register.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentErrorSignUpDialogBinding

class ErrorSignUpDialogFragment : DialogFragment(R.layout.fragment_error_sign_up_dialog) {

    private var binding: FragmentErrorSignUpDialogBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentErrorSignUpDialogBinding.bind(view)
        setupDialog()
        retry()
    }

    private fun setupDialog() {
        dialog?.window?.setLayout(WindowManager.LayoutParams(250).width, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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