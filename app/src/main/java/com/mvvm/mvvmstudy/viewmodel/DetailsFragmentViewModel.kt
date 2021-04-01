package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessActionCallback
import com.mvvm.mvvmstudy.model.observers.OnSuccessDisposableSingleObserver
import com.mvvm.mvvmstudy.useCases.singleUseCases.DataObjectDetailsUseCase

class DetailsFragmentViewModel(private val useCase : DataObjectDetailsUseCase) : ViewModel() {

    var currentObject : MutableLiveData<DataObject> = MutableLiveData()

    fun getObject(objectId : Long) {
        useCase.execute(OnSuccessDisposableSingleObserver(object : OnSuccessActionCallback<DataObject>{
            override fun onSuccessDo(`object`: DataObject) {
                currentObject.postValue(`object`)
            }
        }), objectId)
    }

    fun disposeUseCase(){
        useCase.dispose()
    }
}