package com.musinsa.jth.presentation.extensions

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.musinsa.jth.presentation.views.base.CustomDialogFragment

fun Activity.isActivityAvailable(): Boolean {
    return !isFinishing
}

fun Activity.showDlg(message: String) {
    if (this is FragmentActivity && this.isActivityAvailable()) {
        val fragmentManager = supportFragmentManager
        CustomDialogFragment(
            message = message,
            onPositiveButtonClick = {
                finish()
            }).show(fragmentManager, "dlg")
    }
}