package com.mvvm.mvvmstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import com.mvvm.mvvmstudy.model.repository.BaseRepository
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.observers.OnCompleteActionCallback
import com.mvvm.mvvmstudy.model.observers.OnCompleteObserver
import com.mvvm.mvvmstudy.model.observers.OnSuccessActionCallback
import com.mvvm.mvvmstudy.model.observers.OnSuccessSingleListObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListFragmentViewModel : ViewModel() {

    var currentObjectList : MutableLiveData<List<DataObject>> = MutableLiveData()
    private val repository : BaseRepository<DataObject> = DataObjectRepository()

    fun updateList(){
        repository.getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(OnSuccessSingleListObserver(object :
                OnSuccessActionCallback<List<DataObject>> {
                override fun onSuccessDo(`object`: List<DataObject>) {
                    currentObjectList.value = `object`
                }
            }))
    }

    fun removePosition(positionId: Long){
        repository.deleteById(positionId)
            .subscribeOn(Schedulers.io())
            .subscribe(OnCompleteObserver(object : OnCompleteActionCallback {
                override fun onCompleteDo() {
                    updateList()
                }
            }))
    }
}