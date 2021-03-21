package com.mvvm.mvvmstudy.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.*
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import io.reactivex.schedulers.Schedulers

class CreationFragmentViewModel : ViewModel() {

    private val repository : DataObjectRepository = DataObjectRepository()

    fun addPosition(name:String, details:String){
        if(isValid(name, details)) {
            val newPosition = DataObject(name, details)
            repository.createOrUpdate(newPosition)
                .subscribeOn(Schedulers.io())
                .subscribe(OnSuccessSingleObserver(object : OnSuccessActionCallback<Long>{
                    override fun onSuccessDo(`object`: Long) {
                       Log.i("Created", "Created object with ID: " + `object`)
                    }
                }))
        }
    }

    private fun isValid(name:String, details: String) : Boolean{
        return (name.isNotEmpty() && details.isNotEmpty())
    }

}