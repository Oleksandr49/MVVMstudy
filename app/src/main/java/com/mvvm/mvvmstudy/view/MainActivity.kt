package com.mvvm.mvvmstudy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mvvm.mvvmstudy.R

class MainActivity : AppCompatActivity() {

    private var navHostFragment:NavHostFragment? = null
    private var navController:NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment?.navController
            if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().let{
               navController?.navigate(R.id.listFragment)
            }
        }
    }
}