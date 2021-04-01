package com.mvvm.mvvmstudy.useCases.singleUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.useCases.baseClasses.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class DataObjectDetailsUseCase (private val repository: DataObjectRepository, workThread: Scheduler, receivingThread: Scheduler)
    : SingleUseCase <DataObject, Long>(workThread, receivingThread) {

    override fun buildUseCaseSingle(params: Long?): Single<DataObject> {
        return params?.let { repository.findById(it) }!!
    }

}