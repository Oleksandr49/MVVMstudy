package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.observers.OnSuccessActionCallback
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleObserver
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsFragmentViewModel(private val repository : DataObjectRepository) : ViewModel() {

    var currentObject : MutableLiveData<DataObject> = MutableLiveData()

    fun getObject(objectId : Long) {
        repository.findById(objectId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(OnSuccessSingleObserver(object : OnSuccessActionCallback<DataObject>{
                override fun onSuccessDo(`object`: DataObject) {
                    currentObject.postValue(`object`)
                }
            }))
    }
}