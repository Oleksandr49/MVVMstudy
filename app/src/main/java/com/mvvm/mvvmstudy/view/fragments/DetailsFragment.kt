package com.mvvm.mvvmstudy.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        fun getInstance(value:Long) = DetailsFragment().also { it.associatedPositionId = value }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)

        savedInstanceState?.let { associatedPositionId = savedInstanceState.getLong("ID")
            viewModel.getObject(associatedPositionId)
        }

        viewModel.currentObject.observe(viewLifecycleOwner, { data ->
            binding?.let {
                it.name.text = data.name
                it.details.text = data.details
            }
        })

            return binding?.let { binding ->
                binding.closeButton.also { button -> button.setOnClickListener{ showFragment(ListFragment()) } }
                binding.editButton.also { button -> button.setOnClickListener{
                    showFragment(EditionFragment.getInstance(associatedPositionId)) } }
                return binding.root }
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
        viewModel.dispose()
        binding = null
    }
}