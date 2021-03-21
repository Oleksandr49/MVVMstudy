package com.mvvm.mvvmstudy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.mvvm.mvvmstudy.R
import com.mvvm.mvvmstudy.view.fragments.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        println("MainActivity onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentPlaceHolder, ListFragment())
        transaction.commit()
    }
}