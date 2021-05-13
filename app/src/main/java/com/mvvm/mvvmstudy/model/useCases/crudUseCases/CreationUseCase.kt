package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.BaseUseCase
import io.reactivex.SingleObserver

class CreationUseCase(private val repository: DataObjectRepository) : BaseUseCase<DataObject, SingleObserver<Long>>() {

    override fun execute(param: DataObject?, observer: SingleObserver<Long>) {
        param?.let {repository.create(it)
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
            .subscribe(observer)}
    }
}