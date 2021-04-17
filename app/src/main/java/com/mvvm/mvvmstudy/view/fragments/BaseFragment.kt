package com.mvvm.mvvmstudy.view.fragments

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

import com.mvvm.mvvmstudy.R

open class BaseFragment : Fragment() {

    fun dismissFragment(){
        parentFragmentManager.popBackStack()
    }

    fun showFragment(fragment: Fragment) {
        parentFragmentManager.let {
            it.beginTransaction().also {transaction ->
                transaction.replace(R.id.fragmentPlaceHolder, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        }

    fun showDialog(dialog: DialogFragment) {
        parentFragmentManager.let { dialog.show(it, "anyTag") }

    }
}