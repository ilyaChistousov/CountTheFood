package ilya.chistousov.countcalories.presentation.register.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentSignInBottomSheetDialogBinding
import ilya.chistousov.countcalories.presentation.register.viewmodel.AccountViewModel

class SignInBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var binding: FragmentSignInBottomSheetDialogBinding? = null

    private lateinit var accountViewModel: AccountViewModel

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val idToken = task.getResult(ApiException::class.java).idToken
            if (idToken != null) {
                accountViewModel.signInWithGoogle(idToken,
                    onSuccess = { findNavController().navigate(
                        R.id.action_signInBottomSheetDialogFragment_to_tabsFragment) },
                    onFailure = { findNavController().navigate(
                        R.id.action_signInBottomSheetDialogFragment_to_errorSignInWithGoogleDialogFragment)})
            }
        } catch (ex: ApiException) {
            Log.d("SIGN_IN", "Google sign in exception = $ex")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        accountViewModel = context.appComponent.factory.create(AccountViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBottomSheetDialogBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginWithGoogle()
        loginWithEmailAndPassword()
    }

    private fun loginWithGoogle() {
        binding!!.loginWithGoogle.setOnClickListener {
            launcher.launch(accountViewModel.googleSignInClient.signInIntent)
        }
    }

    private fun loginWithEmailAndPassword() {
        binding!!.loginWithEmail.setOnClickListener {
            findNavController().navigate(
                R.id.action_signINBottomSheetDialogFragment_to_signInWithEmailAndPasswordFragment)
        }
    }
}