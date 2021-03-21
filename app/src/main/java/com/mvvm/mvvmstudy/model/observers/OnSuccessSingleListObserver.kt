package com.mvvm.mvvmstudy.model.observers

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class OnSuccessSingleListObserver(val callback : OnSuccessActionCallback<List<DataObject>>) : SingleObserver<List<DataObject>> {

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
    }

    override fun onSuccess(t: List<DataObject>) {
        callback.onSuccessDo(t)
    }
}