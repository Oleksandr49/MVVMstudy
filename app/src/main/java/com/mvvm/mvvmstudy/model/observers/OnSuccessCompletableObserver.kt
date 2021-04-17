package com.mvvm.mvvmstudy.model.observers

import android.util.Log
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

class OnSuccessCompletableObserver(private val onCompleteAction : ()->Unit?, val disposableAction: (disposable:Disposable)->Unit?) : CompletableObserver{
    override fun onSubscribe(d: Disposable) {
        disposableAction(d)
    }

    override fun onComplete() {
        onCompleteAction()
    }

    override fun onError(e: Throwable) {
        Log.i("Observer", e.message.toString())
    }
}