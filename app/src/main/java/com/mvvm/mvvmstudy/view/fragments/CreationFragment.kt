package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialogCallback
import com.mvvm.mvvmstudy.view.dialogs.InputNotValidDialog
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreationFragment : BaseFragment() {

    val viewModel : CreationFragmentViewModel by viewModel()
    var objectNameInput : EditText? = null
    var objectDetailsInput : EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.getBundle("CurrentState").let {
            objectNameInput?.setText(it?.getCharSequence("Name"))
            objectDetailsInput?.setText(it?.getCharSequence("Details"))
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.creation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        objectNameInput = view.findViewById(R.id.objectNameInput)
        objectDetailsInput = view.findViewById(R.id.objectDetailsInput)
        val addButton : Button = view.findViewById(R.id.ADD)
        addButton.setOnClickListener{
            val name = objectNameInput?.text.toString()
            val details = objectNameInput?.text.toString()
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
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentState = Bundle()
        currentState.putCharSequence("Name", objectNameInput?.text.toString())
        currentState.putCharSequence("Details", objectDetailsInput?.text.toString())
        outState.putBundle("CurrentState", currentState)
    }
}