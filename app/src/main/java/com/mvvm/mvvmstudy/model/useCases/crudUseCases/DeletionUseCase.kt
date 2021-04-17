package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.BaseUseCase
import io.reactivex.CompletableObserver

class DeletionUseCase(private val repository: DataObjectRepository) : BaseUseCase<Long, CompletableObserver>() {

    override fun execute(param: Long?, observer: CompletableObserver) {
         param?.let {
            repository.deleteById(it)
                .subscribeOn(threadExecutorScheduler)
                .observeOn(postExecutionThreadScheduler)
                .subscribe()
        }
    }
}