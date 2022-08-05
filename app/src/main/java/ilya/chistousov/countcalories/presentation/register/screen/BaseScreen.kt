package ilya.chistousov.countcalories.presentation.register.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ilya.chistousov.countcalories.appComponent
import ilya.chistousov.countcalories.databinding.FragmentRegisterContainerBinding
import ilya.chistousov.countcalories.presentation.register.fragment.RegisterFragmentContainer
import ilya.chistousov.countcalories.presentation.register.viewmodel.CreateProfileViewModel

abstract class BaseScreen<VB : ViewBinding>
    (private val bindingInflater: (inflater: LayoutInflater) -> VB) : Fragment() {

    private var _binding: VB? = null

    val binding: VB
        get() = _binding as VB

    private var _parentBinding: FragmentRegisterContainerBinding? = null

    val parentBinding: FragmentRegisterContainerBinding
        get() = _parentBinding as FragmentRegisterContainerBinding

    private var _createProfileViewModel: CreateProfileViewModel? = null

    val createProfileViewModel: CreateProfileViewModel get() = _createProfileViewModel!!

    override fun onAttach(context: Context) {
        _createProfileViewModel = context.appComponent.factory.create(CreateProfileViewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        _parentBinding = (parentFragment as RegisterFragmentContainer).binding

        if (_binding == null) {
            throw IllegalArgumentException("binding cannot be null")
        }

        return binding.root
    }
}