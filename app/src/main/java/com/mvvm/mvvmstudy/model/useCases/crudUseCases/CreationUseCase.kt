package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.IOUseCase

class CreationUseCase(private val repository: DataObjectRepository) : IOUseCase() {

    fun create(dataObject: DataObject) {
        super.addDisposable(repository.create(dataObject)
                .subscribeOn(super.threadExecutorScheduler)
                .observeOn(super.postExecutionThreadScheduler)
                .subscribe())
    }
}