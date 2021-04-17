package com.mvvm.mvvmstudy.view.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.mvvm.mvvmstudy.databinding.ListFragmentBinding
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.view.adapter.ListFragmentAdapter
import com.mvvm.mvvmstudy.view.adapter.ViewCallback
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.viewmodel.ListFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment(){

    private val viewModel : ListFragmentViewModel by viewModel()
    var adapter: ListFragmentAdapter? = null
    private var binding : ListFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.currentObjectList.observe(viewLifecycleOwner, { list -> adapter?.updateList(list)}  )
        binding = ListFragmentBinding.inflate(inflater, container, false)

        viewModel.updateList()

        return binding?.let { binding -> binding.recyclerView.also { recyclerView -> recyclerView.adapter = ListFragmentAdapter().also {
            it.viewCallback =  object : ViewCallback {
                override fun removePosition(positionID: Long) { showDialog(ConfirmationDialog{viewModel.removePosition(positionID)}) }
                override fun positionDetails(positionID: Long) { showFragment(DetailsFragment.getInstance(positionID)) }
            }
            adapter = it
        }
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.left = 10
                    outRect.right = 10
                    outRect.bottom = 15
                    outRect.top = 15
                }
            })
        }
            binding.fab.also { floatingActionButton -> floatingActionButton.setOnClickListener { showFragment(CreationFragment()) } }

            return binding.root
        }
    }

    override fun onResume() {
        viewModel.updateList()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.dispose()
        binding = null
    }
}