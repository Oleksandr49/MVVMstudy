package com.mvvm.mvvmstudy.view.fragments

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

import com.mvvm.mvvmstudy.R

open class BaseFragment : Fragment() {

    fun dismissFragment(){
        parentFragment?.fragmentManager?.popBackStack()
    }

    fun showFragment(fragment: Fragment) {
        parentFragment?.fragmentManager?.let {
            it.beginTransaction().also {transcation ->
                transcation.replace(R.id.fragmentPlaceHolder, fragment)
                transcation.addToBackStack(null)
                transcation.commit()
            }
        }
        }

    fun showDialog(dialog: DialogFragment) {
        parentFragment?.fragmentManager?.let { dialog.show(it, "anyTag") }

    }
}