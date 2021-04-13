package com.mvvm.mvvmstudy.model.useCases.baseClasses

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class IOUseCase(workThread: Scheduler = Schedulers.io(),
                         receivingThread: Scheduler = AndroidSchedulers.mainThread()) : BaseUseCase(workThread, receivingThread)