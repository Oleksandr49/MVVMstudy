package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel

class CreationFragment : BaseFragment() {

    lateinit var viewModel : CreationFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(CreationFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.creation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val objectNameInput : EditText = view.findViewById(R.id.objectNameInput)
        val objectDetailsInput : EditText = view.findViewById(R.id.objectDetailsInput)
        val addButton : Button = view.findViewById(R.id.ADD)
        addButton.setOnClickListener{
            viewModel.addPosition(objectNameInput.text.toString(), objectDetailsInput.text.toString())
            dismissFragment()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}