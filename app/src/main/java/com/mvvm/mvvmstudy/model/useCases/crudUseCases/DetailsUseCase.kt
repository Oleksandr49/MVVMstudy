package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.IOUseCase
import io.reactivex.SingleObserver

class DetailsUseCase(private val repository: DataObjectRepository) : IOUseCase() {

    fun findById(dataObjectID: Long, observer: SingleObserver<DataObject>) {
         repository.findById(dataObjectID)
                .subscribeOn(super.threadExecutorScheduler)
                .observeOn(super.postExecutionThreadScheduler)
                .subscribe(observer)
    }
}