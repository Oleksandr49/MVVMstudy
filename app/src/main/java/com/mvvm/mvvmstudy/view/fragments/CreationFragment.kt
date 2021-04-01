package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.mvvm.mvvmstudy.databinding.CreationFragmentBinding
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialogCallback
import com.mvvm.mvvmstudy.view.dialogs.InputNotValidDialog
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreationFragment : BaseFragment() {

    val viewModel : CreationFragmentViewModel by viewModel()
    private var binding : CreationFragmentBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        savedInstanceState?.getBundle("CurrentState")?.let {
            binding?.objectNameInput?.setText(it.getCharSequence("Name"))
            binding?.objectDetailsInput?.setText(it.getCharSequence("Details"))
        }

        binding = CreationFragmentBinding.inflate(inflater, container, false)

        val addButton : Button? = binding?.ADD
        addButton?.setOnClickListener{
            val name = binding?.objectNameInput?.text.toString()
            val details = binding?.objectDetailsInput?.text.toString()
            if(viewModel.isValid(name, details)){
                showDialog(ConfirmationDialog(object : ConfirmationDialogCallback {
                    override fun onConfirm() {
                        viewModel.addPosition(name, details)
                        dismissFragment()
                    }
                }))
            }
            else {
                showDialog(InputNotValidDialog())
            }
        }

        return binding?.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentState = Bundle()
        currentState.putCharSequence("Name", binding?.objectNameInput?.text.toString())
        currentState.putCharSequence("Details", binding?.objectDetailsInput?.text.toString())
        outState.putBundle("CurrentState", currentState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        viewModel.disposeUseCase()
    }
}