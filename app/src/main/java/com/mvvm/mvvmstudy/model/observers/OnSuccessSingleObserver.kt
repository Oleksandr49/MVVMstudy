package com.mvvm.mvvmstudy.model.observers

import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class OnSuccessSingleObserver<T>(private val callbackAction: OnSuccessActionCallback<T>) : SingleObserver<T> {


    override fun onSubscribe(d: Disposable) {
        Log.i("onSubscribe", "OnSuccessSingleEmptyObserver, onSubscribe()")
    }

    override fun onError(e: Throwable) {
        e.message?.let { Log.i("Error", it) }
    }

    override fun onSuccess(t: T) {
        callbackAction.onSuccessDo(t)
    }
}