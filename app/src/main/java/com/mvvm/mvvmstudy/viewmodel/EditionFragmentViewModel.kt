package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessActionCallback
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditionFragmentViewModel : ViewModel() {

    private val repository : DataObjectRepository = DataObjectRepository()
    var currentObject : MutableLiveData<DataObject> = MutableLiveData()

    fun getObject(objectId : Long) {
        repository.findById(objectId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(OnSuccessSingleObserver(object : OnSuccessActionCallback<DataObject> {
                override fun onSuccessDo(`object`: DataObject) {
                    currentObject.postValue(`object`)
                }
            }))
    }

    fun confirmChanges(editedName: String, editedDetails: String){
        val editedObject: DataObject? = currentObject.value
        editedObject?.name = editedName
        editedObject?.details = editedDetails
        if (editedObject != null) {
            repository.update(editedObject)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
        }
    }

    fun isValid(editedName: String, editedDetails: String) = (notSame(editedName, editedDetails) && notEmpty(editedName, editedDetails))

    private fun notSame(editedName: String, editedDetails: String) = (editedName != currentObject.value?.name || editedDetails !=currentObject.value?.details)

    private fun notEmpty(editedName: String, editedDetails: String) = (editedName.isNotEmpty() && editedDetails.isNotEmpty())

}