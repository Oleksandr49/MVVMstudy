package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.mvvm.mvvmstudy.databinding.DetailsFragmentBinding
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.viewmodel.DetailsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment() {

    private val viewModel : DetailsFragmentViewModel by viewModel()
    var associatedPositionId : Long = 0
    private var binding : DetailsFragmentBinding? = null

    companion object{
        fun getInstance(value:Long) : DetailsFragment{
            val fragment = DetailsFragment()
            fragment.associatedPositionId = value
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.currentObject.observe(viewLifecycleOwner, Observer<DataObject>{
                data -> binding?.name?.text = data.name
            binding?.details?.text = data.details
        })

        if(savedInstanceState != null){
            associatedPositionId = savedInstanceState.getLong("ID")
        }

        viewModel.getObject(associatedPositionId)
        binding = DetailsFragmentBinding.inflate(inflater, container, false)

        val closeButton : Button? = binding?.closeButton
        closeButton?.setOnClickListener{
            dismissFragment()
        }
        val editButton : Button? = binding?.editButton
        editButton?.setOnClickListener{
            showFragment(EditionFragment.getInstance(associatedPositionId))
        }
        return binding?.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("ID", associatedPositionId)
    }

    override fun onResume() {
        viewModel.getObject(associatedPositionId)
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        viewModel.disposeUseCase()
    }
}