package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.IOUseCase

class UpdateUseCase (private val repository: DataObjectRepository) : IOUseCase() {

    fun update (newDataObject: DataObject) {
        super.addDisposable(repository.update(newDataObject)
                .subscribeOn(super.threadExecutorScheduler)
                .observeOn(super.postExecutionThreadScheduler)
                .subscribe())
    }
}