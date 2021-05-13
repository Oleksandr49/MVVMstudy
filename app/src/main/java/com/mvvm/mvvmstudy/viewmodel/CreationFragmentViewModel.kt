package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.CreationUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class CreationFragmentViewModel(private val useCase : CreationUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

     fun addPosition(name:String, details:String){
         DataObject(name = name, details = details).also {useCase.execute(it,
             OnSuccessSingleObserver({},{ disposable -> addToComposite(disposable)}))
         }
    }

    private fun addToComposite(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun dispose(){
        compositeDisposable.dispose()
    }

    fun isValid(name:String, details: String) = (name.isNotEmpty() && details.isNotEmpty())
}