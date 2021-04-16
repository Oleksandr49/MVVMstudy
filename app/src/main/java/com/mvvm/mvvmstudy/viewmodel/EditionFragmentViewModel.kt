package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.DetailsUseCase
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.UpdateUseCase
import javax.inject.Inject

class EditionFragmentViewModel @Inject constructor (private val detailsUseCase : DetailsUseCase, private val updateUseCase : UpdateUseCase) : ViewModel() {

    var currentObject : MutableLiveData<DataObject> = MutableLiveData()

    fun getObject(objectId : Long) {
        detailsUseCase.findById(objectId, OnSuccessSingleObserver{ result -> currentObject.postValue(result) })
    }

    fun confirmChanges(editedName: String, editedDetails: String){
        currentObject.value.also {dataObject ->
        dataObject?.let { it.name = editedName
            it.details = editedDetails
                updateUseCase.update(it)
        }
        }
    }

    fun isValid(editedName: String, editedDetails: String) = editedName.isNotEmpty() && editedDetails.isNotEmpty()
}

