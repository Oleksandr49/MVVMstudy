package com.mvvm.mvvmstudy.model.observers

import android.util.Log
import io.reactivex.observers.DisposableCompletableObserver

class EmptyDisposableCompletableObserver : DisposableCompletableObserver() {
    override fun onComplete() {
        Log.i("EmptyDisposable", "onComplete")
    }

    override fun onError(e: Throwable) {
        Log.i("EmptyDisposable", "onError")
    }
}