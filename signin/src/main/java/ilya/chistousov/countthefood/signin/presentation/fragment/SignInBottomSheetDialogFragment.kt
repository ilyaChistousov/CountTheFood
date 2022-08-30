package ilya.chistousov.countthefood.signin.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ilya.chistousov.countthefood.signin.databinding.FragmentSignInBottomSheetDialogBinding
import ilya.chistousov.countthefood.signin.di.SignInComponentViewModel
import ilya.chistousov.countthefood.signin.presentation.viewmodel.SignInViewModel

class SignInBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var binding: FragmentSignInBottomSheetDialogBinding? = null

    private lateinit var signInViewModel: SignInViewModel

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val idToken = task.getResult(ApiException::class.java).idToken
            if (idToken != null) {
                signInViewModel.signInWithGoogle(idToken,
                    onSuccess = { findNavController().navigate(
                        ilya.chistousov.countthefood.core.R.id.action_global_tabsFragment
                    ) },
                    onFailure = { findNavController().navigate(
                        ilya.chistousov.countthefood.core.R.id.action_signInBottomSheetDialogFragment_to_errorSignInWithGoogleDialogFragment)})
            }
        } catch (ex: ApiException) {
            Log.d("SIGN_IN", "Google sign in exception = $ex")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signInViewModel = ViewModelProvider(this)
            .get<SignInComponentViewModel>()
            .signInComponent.factory.create(SignInViewModel::class.java)
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
            launcher.launch(signInViewModel.googleSignInClient.signInIntent)
        }
    }

    private fun loginWithEmailAndPassword() {
        binding!!.loginWithEmail.setOnClickListener {
            findNavController().navigate(
                ilya.chistousov.countthefood.core.R.id.action_signInBottomSheetDialogFragment_to_signInWithEmailAndPasswordFragment)
        }
    }
}