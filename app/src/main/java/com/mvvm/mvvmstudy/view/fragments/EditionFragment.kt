package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialogCallback
import com.mvvm.mvvmstudy.view.dialogs.InputNotValidDialog
import com.mvvm.mvvmstudy.viewmodel.EditionFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditionFragment : BaseFragment() {

    val viewModel : EditionFragmentViewModel by viewModel()
    var nameDisplay : EditText? = null
    var detailsDisplay : EditText? = null
    var associatedObjectId : Long = 0

    companion object{
        fun getInstance(value:Long) : EditionFragment{
            val fragment = EditionFragment()
            fragment.associatedObjectId = value
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.currentObject.observe(this, Observer<DataObject>{
                data -> nameDisplay?.setText(data.name)
                        detailsDisplay?.setText(data.details)
        })
        viewModel.getObject(associatedObjectId)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nameDisplay = view.findViewById(R.id.objectNameEdit)
        detailsDisplay = view.findViewById(R.id.objectDetailsEdit)

        val cancelButton : Button = view.findViewById(R.id.Cancel)
        cancelButton.setOnClickListener{
            dismissFragment()
        }

        val confirmationButton : Button = view.findViewById(R.id.Confirm)
        confirmationButton.setOnClickListener{
            val editedName:String = nameDisplay?.text.toString()
            val editedDetails:String = detailsDisplay?.text.toString()

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveCurrentObjectState(nameDisplay?.text.toString(), detailsDisplay?.text.toString())
    }
}