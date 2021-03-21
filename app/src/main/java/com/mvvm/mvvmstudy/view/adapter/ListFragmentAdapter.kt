package com.mvvm.mvvmstudy.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.DataObjectDiffUtilCallbackImpl

class ListFragmentAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var itemsList : List<DataObject> = ArrayList()
    lateinit var viewCallback : ViewCallback


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.objectName.text = itemsList[position].name
        holder.objectName.setOnClickListener {
            viewCallback.positionDetails(itemsList[holder.adapterPosition].id!!)
        }
        holder.removeButton.setOnClickListener {
            viewCallback.removePosition(itemsList[holder.adapterPosition].id!!)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun updateList(updatedList: List<DataObject>){
        val result = DiffUtil.calculateDiff(DataObjectDiffUtilCallbackImpl(itemsList, updatedList))
        itemsList = updatedList
        result.dispatchUpdatesTo(this)
    }



}