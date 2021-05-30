package com.WrapX.vcentremap.utils

import android.R
import android.app.Activity
import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utills {
    fun showSnackBar(activity: Activity, message: String, action: String? = null,
                     actionListener: View.OnClickListener? = null, duration: Int = Snackbar.LENGTH_SHORT) {
        val snackBar = Snackbar.make(activity.findViewById(R.id.content), message, duration)
            .setBackgroundTint(Color.parseColor("#FF0000"))
            .setTextColor(Color.WHITE)
        if (action != null && actionListener!=null) {
            snackBar.setAction(action, actionListener)
        }
        snackBar.show()
    }
}