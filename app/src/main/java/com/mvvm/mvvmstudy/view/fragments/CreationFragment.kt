package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mvvm.mvvmstudy.databinding.CreationFragmentBinding
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.InputNotValidDialog
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreationFragment : BaseFragment() {

    private val viewModel : CreationFragmentViewModel by viewModel()
    private var binding : CreationFragmentBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreationFragmentBinding.inflate(inflater, container, false)

        savedInstanceState?.getBundle("CurrentState")?.let {
            binding?.let{binding ->
                binding.objectNameInput.setText(it.getCharSequence("Name"))
                binding.objectDetailsInput.setText(it.getCharSequence("Details"))
            }
        }

        return binding?.let { binding -> binding.ADD.also{ button -> button.setOnClickListener {
            val name = binding.objectNameInput.text.toString()
            val details = binding.objectDetailsInput.text.toString()

            if(viewModel.isValid(name, details)){
                showDialog(ConfirmationDialog {
                    viewModel.addPosition(name, details)
                    CreationFragmentDirections.backToListFragment().also { findNavController().navigate(it) }
                })
            }
            else showDialog(InputNotValidDialog()) }
        }
            return binding.root
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Bundle().also {bundle ->
            binding?.let {
                bundle.putCharSequence("Name", it.objectNameInput.text.toString())
                bundle.putCharSequence("Details", it.objectDetailsInput.text.toString())
                outState.putBundle("CurrentState", bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.dispose()
        binding = null
    }
}