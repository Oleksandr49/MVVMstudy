package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessCompletableObserver
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.DetailsUseCase
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.UpdateUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class EditionFragmentViewModel (private val detailsUseCase : DetailsUseCase, private val updateUseCase : UpdateUseCase) : ViewModel() {

    var currentObject : MutableLiveData<DataObject> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getObject(objectId : Long) {
        detailsUseCase.execute(objectId,
            OnSuccessSingleObserver({result -> currentObject.postValue(result)}, {disposable -> addToComposite(disposable)}))
    }

    fun confirmChanges(editedName: String, editedDetails: String){
        currentObject.value.also {dataObject ->
        dataObject?.let { it.name = editedName
            it.details = editedDetails
                updateUseCase.execute(it, OnSuccessCompletableObserver({},{disposable -> addToComposite(disposable)}))
        }
        }
    }

    fun dispose(){
        compositeDisposable.dispose()
    }

    private fun addToComposite(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun isValid(editedName: String, editedDetails: String) = editedName.isNotEmpty() && editedDetails.isNotEmpty()
}

