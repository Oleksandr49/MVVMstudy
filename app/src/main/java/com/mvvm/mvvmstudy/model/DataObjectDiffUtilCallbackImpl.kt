package com.mvvm.mvvmstudy.model

import androidx.recyclerview.widget.DiffUtil
import com.mvvm.mvvmstudy.model.domainModel.DataObject

class DataObjectDiffUtilCallbackImpl(private val oldList: List<DataObject>, private val newList: List<DataObject>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return isSameId(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newObject : DataObject = newList[newItemPosition]
        val oldObject : DataObject = oldList[oldItemPosition]
        return (isSameId(oldObject, newObject) && isSameName(oldObject, newObject) && isSameDetails(oldObject, newObject))
    }

    private fun isSameId(oldObject: DataObject, newObject: DataObject) : Boolean{
        return oldObject.id == newObject.id
    }

    private fun isSameName(oldObject: DataObject, newObject: DataObject) : Boolean{
        return oldObject.name == newObject.name
    }

    private fun isSameDetails(oldObject: DataObject, newObject: DataObject) : Boolean{
        return oldObject.details == newObject.details
    }
}