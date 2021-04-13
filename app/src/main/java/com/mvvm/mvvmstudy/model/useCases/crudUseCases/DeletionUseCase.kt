package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.IOUseCase

class DeletionUseCase(private val repository: DataObjectRepository) : IOUseCase() {

    fun delete(dataObjectID: Long) {
        super.addDisposable(repository.deleteById(dataObjectID)
                .subscribeOn(super.threadExecutorScheduler)
                .observeOn(super.postExecutionThreadScheduler)
                .subscribe())
    }
}