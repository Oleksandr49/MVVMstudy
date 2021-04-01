package com.mvvm.mvvmstudy.useCases.completabeUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.useCases.baseClasses.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler

class DataObjectUpdateUseCase (private val repository: DataObjectRepository, workThread: Scheduler, receivingThread: Scheduler)
    : CompletableUseCase<DataObject>(workThread, receivingThread) {

    override fun buildUseCaseCompletable(params: DataObject): Completable {
        return repository.update(params)
    }
}