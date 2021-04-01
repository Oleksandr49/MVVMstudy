package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.EmptyDisposableSingleObserver
import com.mvvm.mvvmstudy.useCases.singleUseCases.DataObjectCreationSingleUseCase


class CreationFragmentViewModel(private val useCase : DataObjectCreationSingleUseCase) : ViewModel() {

     fun addPosition(name:String, details:String){
            val newPosition = DataObject(name = name, details = details)
            useCase.execute(EmptyDisposableSingleObserver(), newPosition)
    }

    fun isValid(name:String, details: String) = (name.isNotEmpty() && details.isNotEmpty())

    fun disposeUseCase(){
        useCase.dispose()
    }

}