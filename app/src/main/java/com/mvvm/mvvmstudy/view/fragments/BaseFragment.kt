package com.mvvm.mvvmstudy.view.fragments

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.mvvm.mvvmstudy.R

open class BaseFragment : Fragment() {

    fun dismissFragment(){
        parentFragmentManager.popBackStack()
    }

    fun showFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().also {
            it.replace(R.id.fragmentPlaceHolder, fragment)
            it.addToBackStack(null)
            it.commit()
        }
        }

    fun showDialog(dialog: DialogFragment) {
        dialog.show(parentFragmentManager, "anyTag")
    }
}