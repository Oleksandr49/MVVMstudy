package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.EmptyDisposableCompletableObserver
import com.mvvm.mvvmstudy.model.observers.OnSuccessActionCallback
import com.mvvm.mvvmstudy.model.observers.OnSuccessDisposableSingleObserver
import com.mvvm.mvvmstudy.useCases.completabeUseCases.DataObjectDeleteUseCase
import com.mvvm.mvvmstudy.useCases.singleUseCases.DataObjectGetListUseCase

class ListFragmentViewModel (private val getListUseCase: DataObjectGetListUseCase, private val deletionUseCase : DataObjectDeleteUseCase) : ViewModel() {

    var currentObjectList : MutableLiveData<List<DataObject>> = MutableLiveData()

    fun updateList(){
        getListUseCase.execute(OnSuccessDisposableSingleObserver(object : OnSuccessActionCallback<List<DataObject>>{
            override fun onSuccessDo(`object`: List<DataObject>) {
                currentObjectList.postValue(`object`)
            }
        }))
    }

    fun removePosition(positionId: Long){
        deletionUseCase.execute(EmptyDisposableCompletableObserver(), positionId)
        updateList()
    }

    fun disposeUseCase(){
        getListUseCase.dispose()
        deletionUseCase.dispose()
    }
}