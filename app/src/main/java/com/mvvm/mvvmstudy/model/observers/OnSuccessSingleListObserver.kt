package com.mvvm.mvvmstudy.model.observers

import android.util.Log
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class OnSuccessSingleListObserver(private val callback : OnSuccessActionCallback<List<DataObject>>) : SingleObserver<List<DataObject>> {

    override fun onSubscribe(d: Disposable) {
        Log.i("onSubscribe", "OnSuccessSingleListObserver, onSubscribe()")
    }

    override fun onError(e: Throwable) {
        e.message?.let { Log.i("Error", it) }
    }

    override fun onSuccess(t: List<DataObject>) {
        callback.onSuccessDo(t)
    }
}