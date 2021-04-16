package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.DetailsUseCase
import javax.inject.Inject

class DetailsFragmentViewModel @Inject constructor(private val useCase : DetailsUseCase) : ViewModel() {

    var currentObject : MutableLiveData<DataObject> = MutableLiveData()

    fun getObject(objectId : Long) {
        useCase.findById(objectId, OnSuccessSingleObserver{ result -> currentObject.postValue(result) })
    }
}