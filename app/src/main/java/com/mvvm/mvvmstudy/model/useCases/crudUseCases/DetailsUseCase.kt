package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.BaseUseCase
import io.reactivex.SingleObserver

class DetailsUseCase(private val repository: DataObjectRepository) : BaseUseCase<Long, SingleObserver<DataObject>>() {


    override fun execute(param: Long?, observer: SingleObserver<DataObject>) {
        param?.let {
                repository.findById(it)
                    .subscribeOn(threadExecutorScheduler)
                    .observeOn(postExecutionThreadScheduler)
                    .subscribe(observer)
        }
    }
}