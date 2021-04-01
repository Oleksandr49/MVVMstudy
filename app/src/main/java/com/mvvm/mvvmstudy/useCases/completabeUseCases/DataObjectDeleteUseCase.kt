package com.mvvm.mvvmstudy.useCases.completabeUseCases

import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.useCases.baseClasses.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler

class DataObjectDeleteUseCase (private val repository: DataObjectRepository, workThread: Scheduler, receivingThread: Scheduler)
    : CompletableUseCase<Long>(workThread, receivingThread)  {

    override fun buildUseCaseCompletable(params: Long): Completable {
        return repository.deleteById(params)
    }
}