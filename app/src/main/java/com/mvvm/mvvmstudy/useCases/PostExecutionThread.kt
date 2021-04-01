package com.mvvm.mvvmstudy.useCases

import io.reactivex.Scheduler




interface PostExecutionThread {

    fun getScheduler(): Scheduler

}