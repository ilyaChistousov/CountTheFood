package ilya.chistousov.countthefood.fragment

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.countthefood.App
import ilya.chistousov.countthefood.R
import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.api.result.Result
import ilya.chistousov.countthefood.api.result.handleResponse
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.basefragment.BaseFragment
import ilya.chistousov.countthefood.databinding.FragmentWelcomeBinding
import ilya.chistousov.countthefood.mapper.ProfileDtoMapper
import ilya.chistousov.countthefood.mapper.ProfileEntityMapper
import ilya.chistousov.countthefood.signin.data.mapper.ProfileResponseMapper
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {

    @Inject
    lateinit var profileService: ProfileService

    @Inject
    lateinit var profileDao: ProfileDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = App()
        profileService = app.appComponent.profileService
    }

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
                ds.color =
                    requireActivity().getColor(ilya.chistousov.countthefood.core.R.color.light_green)
                ds.isUnderlineText = false
            }

            override fun onClick(widget: View) {
                findNavController().navigate(ilya.chistousov.countthefood.core.R.id.action_welcomeFragment_to_sign_in_graph)
            }
        }
        spannableText.setSpan(
            clickableSpan,
            text.length - 5,
            text.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding.signInText.text = spannableText
        binding.signInText.movementMethod = LinkMovementMethod.getInstance()
    }
}