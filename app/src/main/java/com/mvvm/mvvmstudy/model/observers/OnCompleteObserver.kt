package com.mvvm.mvvmstudy.model.observers

import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

class OnCompleteObserver (val onCompleteCallback : OnCompleteActionCallback) : CompletableObserver {

    override fun onSubscribe(d: Disposable) {

    }

    override fun onComplete() {
        onCompleteCallback.onCompleteDo()
    }

    override fun onError(e: Throwable) {
        println(e.message)
    }
}