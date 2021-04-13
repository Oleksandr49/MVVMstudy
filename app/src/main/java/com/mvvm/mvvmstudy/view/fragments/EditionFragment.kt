package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mvvm.mvvmstudy.databinding.EditionFragmentBinding
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.InputNotValidDialog
import com.mvvm.mvvmstudy.viewmodel.EditionFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditionFragment : BaseFragment() {

    private val viewModel : EditionFragmentViewModel by viewModel()
    var associatedObjectId : Long = 0
    private var binding : EditionFragmentBinding? = null

    companion object{
        fun getInstance(value:Long) = EditionFragment().also { it.associatedObjectId = value }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EditionFragmentBinding.inflate(inflater, container, false)
        viewModel.currentObject.observe(viewLifecycleOwner, Observer<DataObject>{
            data -> binding?.let { it.objectNameEdit.setText(data.name)
            it.objectDetailsEdit.setText(data.details)  }
        })

        if(savedInstanceState != null){
            savedInstanceState.getBundle("CurrentState")?.let {
                associatedObjectId = it.getLong("ID")
                DataObject(associatedObjectId, it.getCharSequence("Name").toString(), it.getCharSequence("Details").toString())
                        .also { dataObject ->  viewModel.currentObject.postValue(dataObject) }
            }
        }
        else viewModel.getObject(associatedObjectId)

        return binding?.let {binding ->
            binding.Cancel.also { it.setOnClickListener{dismissFragment()} }

            binding.Confirm.also { it.setOnClickListener{
                val editedName = binding.objectNameEdit.text.toString()
                val editedDetails = binding.objectDetailsEdit.text.toString()

                if(viewModel.isValid(editedName, editedDetails)) showDialog(ConfirmationDialog {viewModel.confirmChanges(editedName, editedDetails)
                        showFragment(DetailsFragment.getInstance(associatedObjectId))})

                else showDialog(InputNotValidDialog())
            }}
            return binding.root}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Bundle().also {bundle ->
            binding?.let {
                bundle.putLong("ID", associatedObjectId)
                bundle.putCharSequence("Name", it.objectNameEdit.text.toString())
                bundle.putCharSequence("Details", it.objectDetailsEdit.text.toString())
                outState.putBundle("CurrentState", bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}