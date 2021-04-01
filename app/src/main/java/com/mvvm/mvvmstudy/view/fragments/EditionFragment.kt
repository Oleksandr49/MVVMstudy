package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.mvvm.mvvmstudy.databinding.EditionFragmentBinding
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialogCallback
import com.mvvm.mvvmstudy.view.dialogs.InputNotValidDialog
import com.mvvm.mvvmstudy.viewmodel.EditionFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditionFragment : BaseFragment() {

    val viewModel : EditionFragmentViewModel by viewModel()
    var associatedObjectId : Long = 0
    private var binding : EditionFragmentBinding? = null

    companion object{
        fun getInstance(value:Long) : EditionFragment{
            val fragment = EditionFragment()
            fragment.associatedObjectId = value
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.currentObject.observe(viewLifecycleOwner, Observer<DataObject>{
                data -> binding?.objectNameEdit?.setText(data.name)
            binding?.objectDetailsEdit?.setText(data.details)
        })
        viewModel.getObject(associatedObjectId)
        binding = EditionFragmentBinding.inflate(inflater, container, false)

        val cancelButton : Button? = binding?.Cancel
        cancelButton?.setOnClickListener{
            dismissFragment()
        }

        val confirmationButton : Button? = binding?.Confirm
        confirmationButton?.setOnClickListener{
            val editedName:String = binding?.objectNameEdit?.text.toString()
            val editedDetails:String = binding?.objectDetailsEdit?.text.toString()

            if(viewModel.isValid(editedName, editedDetails)) {
                showDialog(ConfirmationDialog(object : ConfirmationDialogCallback {
                    override fun onConfirm() {
                        viewModel.confirmChanges(editedName, editedDetails)
                        dismissFragment()
                    }
                }))
            }
            else{
                showDialog(InputNotValidDialog())
            }
        }

        return binding?.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveCurrentObjectState(binding?.objectNameEdit?.text.toString(), binding?.objectDetailsEdit?.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        viewModel.disposeUseCase()
    }
}