package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessCompletableObserver
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.DeletionUseCase
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.GetAllUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class ListFragmentViewModel (private val getListUseCase: GetAllUseCase, private val deletionUseCase : DeletionUseCase) : ViewModel() {

    val currentObjectList : MutableLiveData<List<DataObject>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun updateList(){
        getListUseCase.execute(observer = OnSuccessSingleObserver({ result ->  currentObjectList.postValue(result)},
            {disposable -> addToComposite(disposable)}))
    }

    fun removePosition(positionId: Long){
        deletionUseCase.execute(positionId, OnSuccessCompletableObserver({},{disposable -> addToComposite(disposable)}))
        updateList()
    }

    fun dispose(){
        compositeDisposable.dispose()
    }

    private fun addToComposite(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
}