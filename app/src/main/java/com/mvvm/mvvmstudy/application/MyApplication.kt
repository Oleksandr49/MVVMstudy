package com.mvvm.mvvmstudy.application

import android.app.Application
import com.mvvm.mvvmstudy.application.dagger.AppComponent
import com.mvvm.mvvmstudy.application.dagger.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(applicationContext) }
    
    override fun onCreate() {
        super.onCreate()
    }
}

