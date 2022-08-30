package ilya.chistousov.countthefood.presentation

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.navigation.fragment.findNavController
import ilya.chistousov.countthefood.R
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        openSignInFragment()
        openSignUpFragment()
    }

    private fun openSignUpFragment() {
        binding.registerButton.setOnClickListener {
            findNavController().navigate(ilya.chistousov.countthefood.core.R.id.action_welcomeFragment_to_sign_up_graph)
        }
    }

    private fun openSignInFragment() {
        val text = getString(R.string.has_account)
        val spannableText = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.color = requireActivity().getColor(ilya.chistousov.countthefood.core.R.color.light_green)
                ds.isUnderlineText = false
            }
            override fun onClick(widget: View) {
                findNavController().navigate(ilya.chistousov.countthefood.core.R.id.action_welcomeFragment_to_sign_in_graph)
            }
        }
        spannableText.setSpan(clickableSpan, text.length - 5, text.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.signInText.text = spannableText
        binding.signInText.movementMethod = LinkMovementMethod.getInstance()
    }
}