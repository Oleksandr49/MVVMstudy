package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.viewmodel.DetailsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment() {

    val viewModel : DetailsFragmentViewModel by viewModel()
    var name : TextView? = null
    var details : TextView? = null
    var associatedPositionId : Long = 0

    companion object{
        fun getInstance(value:Long) : DetailsFragment{
            val fragment = DetailsFragment()
            fragment.associatedPositionId = value
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            viewModel.currentObject.observe(this, Observer<DataObject>{
                data -> name?.text = data.name
                details?.text = data.details
            })

        if(savedInstanceState != null){
            associatedPositionId = savedInstanceState.getLong("ID")
            viewModel.getObject(associatedPositionId)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.details_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        name = view.findViewById(R.id.name)
        details = view.findViewById(R.id.details)
        val closeButton : Button = view.findViewById(R.id.closeButton)
        closeButton.setOnClickListener{
            dismissFragment()
        }
        val editButton : Button = view.findViewById(R.id.editButton)
        editButton.setOnClickListener{
            showFragment(EditionFragment.getInstance(associatedPositionId))
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("ID", associatedPositionId)
    }

    override fun onResume() {
        viewModel.getObject(associatedPositionId)
        super.onResume()
    }
}