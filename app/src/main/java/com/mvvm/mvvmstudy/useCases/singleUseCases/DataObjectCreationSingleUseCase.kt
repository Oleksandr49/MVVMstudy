package com.mvvm.mvvmstudy.useCases.singleUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.useCases.baseClasses.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class DataObjectCreationSingleUseCase(private val repository: DataObjectRepository, workThread: Scheduler, receivingThread: Scheduler)
    : SingleUseCase<Long, DataObject> (workThread, receivingThread) {

    override fun buildUseCaseSingle(params:DataObject?): Single <Long> {
            return params?.let { repository.create(it) }!!
    }
}