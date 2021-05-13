package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.BaseUseCase
import io.reactivex.CompletableObserver

class UpdateUseCase (private val repository: DataObjectRepository) : BaseUseCase<DataObject, CompletableObserver>() {

    override fun execute(param: DataObject?, observer: CompletableObserver) {
        param?.let {
            repository.update(it)
                .subscribeOn(threadExecutorScheduler)
                .observeOn(postExecutionThreadScheduler)
                .subscribe(observer)
        }
    }
}