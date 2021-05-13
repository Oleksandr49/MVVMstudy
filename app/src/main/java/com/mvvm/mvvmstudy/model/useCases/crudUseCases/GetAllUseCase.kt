package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.BaseUseCase
import io.reactivex.SingleObserver

class GetAllUseCase(private val repository: DataObjectRepository) : BaseUseCase<Long, SingleObserver<List<DataObject>>>() {

    override fun execute(param: Long?, observer: SingleObserver<List<DataObject>>) {
         repository.getAll()
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
            .subscribe(observer)
    }
}