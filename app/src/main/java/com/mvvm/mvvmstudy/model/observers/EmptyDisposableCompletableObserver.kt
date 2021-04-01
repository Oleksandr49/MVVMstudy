package com.mvvm.mvvmstudy.model.observers

import io.reactivex.observers.DisposableCompletableObserver

class EmptyDisposableCompletableObserver : DisposableCompletableObserver() {
    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }
}