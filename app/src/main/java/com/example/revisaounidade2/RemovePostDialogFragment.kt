package com.example.revisaounidade2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class RemovePostDialogFragment(val callback: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(getString(R.string.delete_post_dialog_title))
                .setPositiveButton(getString(R.string.confirm_post_delete), DialogInterface.OnClickListener { dialog, id ->
                    callback()
                })
                .setNegativeButton(getString(R.string.cancel_post_delete), DialogInterface.OnClickListener { dialog, id ->
                    dismiss()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}