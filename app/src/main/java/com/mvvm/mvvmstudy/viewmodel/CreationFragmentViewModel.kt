package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import io.reactivex.schedulers.Schedulers

class CreationFragmentViewModel(private val repository : DataObjectRepository) : ViewModel() {

     fun addPosition(name:String, details:String){
            val newPosition = DataObject(null, name, details)
            repository.create(newPosition)
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun isValid(name:String, details: String) = (name.isNotEmpty() && details.isNotEmpty())

}