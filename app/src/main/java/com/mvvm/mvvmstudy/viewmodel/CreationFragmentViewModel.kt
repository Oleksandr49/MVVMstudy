package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnCompleteActionCallback
import com.mvvm.mvvmstudy.model.observers.OnCompleteObserver
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import io.reactivex.schedulers.Schedulers

class CreationFragmentViewModel : ViewModel() {

    private val repository : DataObjectRepository = DataObjectRepository()

    fun addPosition(name:String, details:String){
        if(isValid(name, details)) {
            val newPosition = DataObject(name, details)
            repository.createOrUpdate(newPosition)
                .subscribeOn(Schedulers.io())
                .subscribe(OnCompleteObserver(object : OnCompleteActionCallback {
                    override fun onCompleteDo() {
                        println("Created")
                    }
                }))
        }
    }

    private fun isValid(name:String, details: String) : Boolean{
        return (name.isNotEmpty() && details.isNotEmpty())
    }

}