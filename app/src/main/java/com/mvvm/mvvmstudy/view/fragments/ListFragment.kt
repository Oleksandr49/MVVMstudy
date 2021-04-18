package com.mvvm.mvvmstudy.view.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.databinding.ListFragmentBinding
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
                override fun positionDetails(positionID: Long) {
                    ListFragmentDirections.goToDetails(positionID).also {action -> findNavController().navigate(action) }
                }
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
            binding.fab.also { floatingActionButton -> floatingActionButton.setOnClickListener {
                ListFragmentDirections.goToCreation().also { findNavController().navigate(it) }
            } }

            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}