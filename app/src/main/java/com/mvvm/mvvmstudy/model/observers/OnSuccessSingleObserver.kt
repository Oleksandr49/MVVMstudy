package com.mvvm.mvvmstudy.model.observers

import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class OnSuccessSingleObserver<T>(val onSuccessAction : (result:T)->Unit) : SingleObserver<T> {

    override fun onSuccess(t: T) {
        onSuccessAction(t)
    }

    override fun onError(e: Throwable) {
        Log.i("Observer", e.message.toString())
    }

    override fun onSubscribe(d: Disposable) {
        Log.i("Observer", "Subscribed")
    }

}