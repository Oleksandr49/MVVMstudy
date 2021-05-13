package com.mvvm.mvvmstudy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.view.fragments.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().let{
                it.replace(R.id.fragmentPlaceHolder, ListFragment())
                it.commit()
            }
        }
    }
}