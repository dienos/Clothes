package com.musinsa.jth.presentation.views.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.musinsa.jth.presentation.R

class CustomDialogFragment(
    private val message: String = "",
    private val onPositiveButtonClick: () -> Unit = {}
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                .setPositiveButton(
                    R.string.confirm
                ) { _, _ ->
                    dismiss()
                    onPositiveButtonClick()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}