package com.mvvm.mvvmstudy.model.useCases.crudUseCases

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.baseClasses.IOUseCase
import io.reactivex.SingleObserver
import javax.inject.Inject

class GetAllUseCase @Inject constructor (private val repository: DataObjectRepository) : IOUseCase() {

    fun getAll(observer: SingleObserver<List<DataObject>>){
        repository.getAll()
                .subscribeOn(super.threadExecutorScheduler)
                .observeOn(super.postExecutionThreadScheduler)
                .subscribe(observer)
    }
}