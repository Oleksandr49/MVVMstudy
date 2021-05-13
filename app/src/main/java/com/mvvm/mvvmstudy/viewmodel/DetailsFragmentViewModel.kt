package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.DetailsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DetailsFragmentViewModel(private val useCase : DetailsUseCase) : ViewModel() {

    var currentObject : MutableLiveData<DataObject> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getObject(objectId : Long) {
        useCase.execute(objectId,
            OnSuccessSingleObserver({result -> currentObject.postValue(result)}, {disposable -> addToComposite(disposable)}))
    }

    fun dispose(){
        compositeDisposable.dispose()
    }

    private fun addToComposite(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
}