package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.CreationUseCase
import javax.inject.Inject


class CreationFragmentViewModel @Inject constructor(private val useCase : CreationUseCase) : ViewModel() {

     fun addPosition(name:String, details:String){
         DataObject(name = name, details = details).also {useCase.create(it)}
    }

    fun isValid(name:String, details: String) = (name.isNotEmpty() && details.isNotEmpty())
}