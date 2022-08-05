package ilya.chistousov.countcalories.presentation.register.screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentEmailBinding
import ilya.chistousov.countcalories.presentation.register.viewmodel.AccountViewModel

class EmailScreen : BaseScreen<FragmentEmailBinding>(
    FragmentEmailBinding::inflate
) {
    private lateinit var accountViewModel: AccountViewModel

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val idToken = task.getResult(ApiException::class.java).idToken
            if (idToken != null) {
                accountViewModel.signUpWithGoogle(idToken,
                    onSuccess = {
                        createProfileViewModel.createProfile()
                        findNavController().navigate(R.id.action_registerFragmentContainer_to_tabsFragment)
                    },
                    onFailure = {
                        findNavController().navigate(
                            R.id.action_registerFragmentContainer_to_errorSignUpDialogFragment
                        )
                    })
            }
        } catch (ex: ApiException) {
            Log.d("SIGN_IN", "Google sign in exception = $ex")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountViewModel = context.appComponent.factory.create(AccountViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        signUpWithGoogle()
        signUpWithEmailAndPassword()
    }

    private fun signUpWithEmailAndPassword() {
        binding.cardViewInputEmail.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragmentContainer_to_registerFragment)
        }
    }

    private fun signUpWithGoogle() {
        binding.cardViewGmail.setOnClickListener {
            launcher.launch(accountViewModel.googleSignInClient.signInIntent)
        }
    }
}