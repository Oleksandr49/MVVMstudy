package com.mvvm.mvvmstudy.useCases.singleUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.useCases.baseClasses.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class DataObjectGetListUseCase  (private val repository: DataObjectRepository, workThread: Scheduler, receivingThread: Scheduler)
    : SingleUseCase <List<DataObject>, Long>(workThread, receivingThread) {

    override fun buildUseCaseSingle(params: Long?): Single<List<DataObject>> {
        return repository.getAll()
    }
}