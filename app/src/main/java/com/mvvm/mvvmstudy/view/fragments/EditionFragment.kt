package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialogCallback
import com.mvvm.mvvmstudy.view.dialogs.InputNotValidDialog
import com.mvvm.mvvmstudy.viewmodel.EditionFragmentViewModel

class EditionFragment(private val associatedObjectId : Long) : BaseFragment() {

    lateinit var viewModel : EditionFragmentViewModel
    lateinit var name : EditText
    lateinit var details : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(EditionFragmentViewModel::class.java)
        viewModel.currentObject.observe(this, Observer<DataObject>{
                data -> name.setText(data.name)
                        details.setText(data.details)
        })
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        name = view.findViewById(R.id.objectNameEdit)
        details = view.findViewById(R.id.objectDetailsEdit)

        val cancelButton : Button = view.findViewById(R.id.Cancel)
        cancelButton.setOnClickListener{
            dismissFragment()
        }

        val confirmationButton : Button = view.findViewById(R.id.Confirm)
        confirmationButton.setOnClickListener{
            val editedName:String = name.text.toString()
            val editedDetails:String = details.text.toString()

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

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        viewModel.getObject(associatedObjectId)
        super.onResume()
    }
}