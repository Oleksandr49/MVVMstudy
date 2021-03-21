package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel
import com.mvvm.mvvmstudy.viewmodel.DetailsFragmentViewModel

class DetailsFragment(private val associatedPositionId : Long): BaseFragment() {

    lateinit var viewModel : DetailsFragmentViewModel
    lateinit var name : TextView
    lateinit var details : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailsFragmentViewModel::class.java)
        viewModel.currentObject.observe(this, Observer<DataObject>{
            data -> name.text = data.name
                    details.text = data.details
        })
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        name = view.findViewById(R.id.name)
        details = view.findViewById(R.id.details)
        val closeButton : Button = view.findViewById(R.id.closeButton)
        closeButton.setOnClickListener{
            dismissFragment()
        }
        val editButton : Button = view.findViewById(R.id.editButton)
        editButton.setOnClickListener{
            showFragment(EditionFragment(associatedPositionId))
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        viewModel.getObject(associatedPositionId)
        super.onResume()
    }
}