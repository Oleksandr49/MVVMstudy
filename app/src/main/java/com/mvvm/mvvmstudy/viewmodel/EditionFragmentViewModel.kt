package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.EmptyDisposableCompletableObserver
import com.mvvm.mvvmstudy.model.observers.OnSuccessActionCallback
import com.mvvm.mvvmstudy.model.observers.OnSuccessDisposableSingleObserver
import com.mvvm.mvvmstudy.useCases.completabeUseCases.DataObjectUpdateUseCase
import com.mvvm.mvvmstudy.useCases.singleUseCases.DataObjectDetailsUseCase

class EditionFragmentViewModel (private val detailsUseCase : DataObjectDetailsUseCase, private val updateUseCase : DataObjectUpdateUseCase) : ViewModel() {

    var currentObject : MutableLiveData<DataObject> = MutableLiveData()

    fun saveCurrentObjectState(editedName: String, editedDetails: String){
        currentObject.value?.name = editedName
        currentObject.value?.details = editedDetails
    }

    fun getObject(objectId : Long) {
        detailsUseCase.execute(OnSuccessDisposableSingleObserver(object : OnSuccessActionCallback<DataObject>{
            override fun onSuccessDo(`object`: DataObject) {
                currentObject.postValue(`object`)
            }
        }), objectId)
    }

    fun confirmChanges(editedName: String, editedDetails: String){
        val editedObject: DataObject? = currentObject.value
        editedObject?.name = editedName
        editedObject?.details = editedDetails
        if (editedObject != null) {
            updateUseCase.execute(EmptyDisposableCompletableObserver(), editedObject)
        }
    }

    fun isValid(editedName: String, editedDetails: String) = editedName.isNotEmpty() && editedDetails.isNotEmpty()

    fun disposeUseCase(){
        detailsUseCase.dispose()
        updateUseCase.dispose()
    }

}

