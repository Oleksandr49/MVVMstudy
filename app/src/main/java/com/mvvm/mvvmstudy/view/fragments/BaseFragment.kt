package com.mvvm.mvvmstudy.view.fragments

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.mvvm.mvvmstudy.R

open class BaseFragment : Fragment() {

    fun showDialog(dialog: DialogFragment) {
        dialog.show(parentFragmentManager, "anyTag")
    }
}