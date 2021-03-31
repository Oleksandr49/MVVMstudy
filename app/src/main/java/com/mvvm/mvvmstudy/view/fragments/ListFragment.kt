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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.view.adapter.ListFragmentAdapter
import com.mvvm.mvvmstudy.view.adapter.ViewCallback
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialog
import com.mvvm.mvvmstudy.view.dialogs.ConfirmationDialogCallback
import com.mvvm.mvvmstudy.viewmodel.ListFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment(){

    val viewModel : ListFragmentViewModel by viewModel()
    var adapter: ListFragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.updateList()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.currentObjectList.observe(viewLifecycleOwner, Observer<List<DataObject>>{ list -> adapter?.updateList(list)}  )
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
        adapter = ListFragmentAdapter()
        adapter?.viewCallback = object : ViewCallback {
            override fun removePosition(positionID: Long) {
                showDialog(ConfirmationDialog(object : ConfirmationDialogCallback{
                    override fun onConfirm() {
                        viewModel.removePosition(positionID)
                    }
                }))
            }
            override fun positionDetails(positionID: Long) {
                showFragment(DetailsFragment.getInstance(positionID))
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            )
            {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.left = 10
                outRect.right = 10
                outRect.bottom = 15
                outRect.top = 15
            }
        })

        val addPosition: FloatingActionButton = view.findViewById(R.id.fab)
        addPosition.setOnClickListener { showFragment(CreationFragment()) }
    }

    override fun onResume() {
        viewModel.updateList()
        super.onResume()
    }
}