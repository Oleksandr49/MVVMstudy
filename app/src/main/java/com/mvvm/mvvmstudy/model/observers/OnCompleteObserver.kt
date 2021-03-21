package com.mvvm.mvvmstudy.model.observers

import android.util.Log
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

class OnCompleteObserver (private val onCompleteCallback : OnCompleteActionCallback) : CompletableObserver {

    override fun onSubscribe(d: Disposable) {
        Log.i("onSubscribe", "OnCompleteObserver, onSubscribe()")
    }

    override fun onComplete() {
        onCompleteCallback.onCompleteDo()
    }

    override fun onError(e: Throwable) {
        e.message?.let { Log.i("Error", it) }
    }
}