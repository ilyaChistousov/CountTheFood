package ilya.chistousov.countthefood.signup.presentation.screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import ilya.chistousov.countthefood.signup.R
import ilya.chistousov.countthefood.signup.databinding.FragmentEmailBinding
import ilya.chistousov.countthefood.signup.di.SignUpComponentViewModel
import ilya.chistousov.countthefood.signup.presentation.viewmodel.SignUpViewModel
import ilya.chistousov.countthefood.signup.utils.EMAIL

class EmailScreen : BaseScreen<FragmentEmailBinding>(
    FragmentEmailBinding::inflate
) {
    private lateinit var signUpViewModel: SignUpViewModel

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val idToken = task.getResult(ApiException::class.java).idToken
            if (idToken != null) {
                signUpViewModel.signUpWithGoogle(idToken,
                    onSuccess = {
                        val googleEmail = task.result.email
                        createProfileViewModel.putString(EMAIL, googleEmail!!)
                        createProfileViewModel.createProfile()
                        findNavController().navigate(ilya.chistousov.countthefood.core.R.id.action_global_loadingScreen)
                    },
                    onFailure = {
                        findNavController().navigate(
                            ilya.chistousov.countthefood.core.R.id.action_signUpFragmentContainer_to_errorSignUpDialogFragment)
                    })
            }
        } catch (ex: ApiException) {
            Log.d("SIGN_IN", "Google sign in exception = $ex")
        }
    }

    override fun onAttach(context: Context) {
        val signUpComponent = ViewModelProvider(this).get<SignUpComponentViewModel>().signUpComponent
        signUpViewModel = signUpComponent.factory.create(SignUpViewModel::class.java)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        signUpWithGoogle()
        signUpWithEmailAndPassword()
    }

    private fun signUpWithEmailAndPassword() {
        binding.cardViewInputEmail.setOnClickListener {
            findNavController().navigate(ilya.chistousov.countthefood.core.R.id.action_signUpFragmentContainer_to_signUpFragment)
        }
    }

    private fun signUpWithGoogle() {
        binding.cardViewGmail.setOnClickListener {
            launcher.launch(signUpViewModel.googleSignInClient.signInIntent)
        }
    }
}