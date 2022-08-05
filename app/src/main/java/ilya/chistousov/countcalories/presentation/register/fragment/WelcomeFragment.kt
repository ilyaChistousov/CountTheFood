package ilya.chistousov.countcalories.presentation.register.fragment

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.databinding.FragmentWelcomeBinding
import ilya.chistousov.countcalories.presentation.basefragment.BaseFragment

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        makeLinks()
        openRegisterFragment()
    }

    private fun openRegisterFragment() {
        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_registerFragmentContainer)
        }
    }

    private fun makeLinks() {
        val text = getString(R.string.has_account)
        val spannableText = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.color = requireActivity().getColor(R.color.light_green)
                ds.isUnderlineText = false
            }
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_welcomeFragment_to_signInBottomSheetDialogFragment)
            }
        }
        spannableText.setSpan(clickableSpan, text.length - 5, text.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.signInText.text = spannableText
        binding.signInText.movementMethod = LinkMovementMethod.getInstance()
    }
}