package com.mvvm.mvvmstudy.view.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.view.adapter.ListFragmentAdapter
import com.mvvm.mvvmstudy.view.adapter.ViewPositionRemovalCallback
import com.mvvm.mvvmstudy.viewmodel.ListFragmentViewModel

class ListFragment : BaseFragment(){

    lateinit var viewModel : ListFragmentViewModel
    lateinit var adapter: ListFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.currentObjectList.observe(this, Observer<List<DataObject>>{ list -> adapter.updateList(list)}  )
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
        adapter = ListFragmentAdapter()
        adapter.viewCallback = object : ViewPositionRemovalCallback {
            override fun removePosition(positionID: Long) {
                viewModel.removePosition(positionID)
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
        addPosition.setOnClickListener {
            val transaction =
                activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentPlaceHolder, CreationFragment())
            transaction?.addToBackStack("ObjectCreation")
            transaction?.commit()
        }
    }

    override fun onResume() {
        viewModel.updateList()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}