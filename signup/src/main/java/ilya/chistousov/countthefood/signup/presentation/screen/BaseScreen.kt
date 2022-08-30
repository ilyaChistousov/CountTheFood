package ilya.chistousov.countthefood.signup.presentation.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewbinding.ViewBinding
import ilya.chistousov.countthefood.signup.databinding.FragmentSignUpContainerBinding
import ilya.chistousov.countthefood.signup.di.SignUpComponentViewModel
import ilya.chistousov.countthefood.signup.presentation.fragment.SignUpFragmentContainer
import ilya.chistousov.countthefood.signup.presentation.viewmodel.CreateProfileViewModel

abstract class BaseScreen<VB : ViewBinding>
    (private val bindingInflater: (inflater: LayoutInflater) -> VB) : Fragment() {

    private var _binding: VB? = null

    val binding: VB
        get() = _binding as VB

    private var _parentBinding: FragmentSignUpContainerBinding? = null

    val parentBinding: FragmentSignUpContainerBinding
        get() = _parentBinding as FragmentSignUpContainerBinding

    private var _createProfileViewModel: CreateProfileViewModel? = null

    val createProfileViewModel: CreateProfileViewModel get() = _createProfileViewModel!!

    override fun onAttach(context: Context) {
        val signUpComponent = ViewModelProvider(this).get<SignUpComponentViewModel>().signUpComponent
        _createProfileViewModel = signUpComponent.factory.create(CreateProfileViewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        _parentBinding = (parentFragment as SignUpFragmentContainer).binding

        if (_binding == null) {
            throw IllegalArgumentException("binding cannot be null")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _parentBinding = null
        _createProfileViewModel = null
    }
}