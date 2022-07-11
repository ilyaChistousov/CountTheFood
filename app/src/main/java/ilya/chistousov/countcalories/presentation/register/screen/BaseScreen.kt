package ilya.chistousov.countcalories.presentation.register.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ilya.chistousov.countcalories.databinding.FragmentViewpagerContainerBinding
import java.lang.IllegalArgumentException

abstract class BaseScreen<VB : ViewBinding>
    (private val bindingInflater: (inflater: LayoutInflater) -> VB) : Fragment() {

    private var _binding: VB? = null

    val binding: VB
        get() = _binding as VB

    private var _parentBinding: FragmentViewpagerContainerBinding? = null

    val parentBinding: FragmentViewpagerContainerBinding
        get() = _parentBinding as FragmentViewpagerContainerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        _parentBinding = (parentFragment as ViewPagerContainer).binding

        if (_binding == null) {
            throw IllegalArgumentException("binding cannot be null")
        }

        return binding.root
    }
}