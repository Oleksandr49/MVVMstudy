package com.mvvm.mvvmstudy.model.observers

import io.reactivex.observers.DisposableSingleObserver

class EmptyDisposableSingleObserver<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {
    }

    override fun onError(e: Throwable) {
    }
}