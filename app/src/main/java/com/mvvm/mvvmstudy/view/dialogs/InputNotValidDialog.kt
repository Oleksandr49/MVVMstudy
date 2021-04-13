package com.mvvm.mvvmstudy.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InputNotValidDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        AlertDialog.Builder(activity).also { it.setMessage("Indicated data is not valid")
                .setNegativeButton("Try again") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            return it.create() }
    }
}