package com.mvvm.mvvmstudy

import android.app.Application
import android.content.Context
import java.lang.Exception

class MyApplication : Application() {

    companion object{

        private var context: Context? = null

        fun getAppContext(): Context {
            if(context != null) return context as Context
            else throw Exception("No App Context Found")
        }
    }


    override fun onCreate() {
        println("Application OnCreate")
        context = applicationContext
        super.onCreate()

    }


    override fun onTerminate() {
        context = null
        super.onTerminate()
    }


}