package com.mvvm.mvvmstudy.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmationDialog(private val onConfirmCallback : () -> Unit): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        AlertDialog.Builder(activity).also {
            it.setMessage("Confirm please")
                    .setPositiveButton("Confirm") { _: DialogInterface?, _: Int -> onConfirmCallback() }
                    .setNegativeButton("Cancel") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            return it.create()
        }
    }
}