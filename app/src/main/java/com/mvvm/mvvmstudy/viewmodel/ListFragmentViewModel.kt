package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.DeletionUseCase
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.GetAllUseCase
import javax.inject.Inject

class ListFragmentViewModel @Inject constructor(private val getListUseCase: GetAllUseCase, private val deletionUseCase : DeletionUseCase) : ViewModel() {

    var currentObjectList : MutableLiveData<List<DataObject>> = MutableLiveData()

    fun updateList(){
        getListUseCase.getAll(OnSuccessSingleObserver{ result ->  currentObjectList.postValue(result)})
    }

    fun removePosition(positionId: Long){
        deletionUseCase.delete(positionId)
        updateList()
    }
}