package com.templateapp.template.services.actions

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.template_android.R
import java.util.*

object Utils {

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager != null) {
            val focusedView = activity.currentFocus
            if (focusedView != null) {
                inputMethodManager.hideSoftInputFromWindow(
                    Objects.requireNonNull<View>(activity.currentFocus).windowToken,
                    0
                )

            }
        }
    }



    fun showToaster(context: Activity, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun isEmpty(etText: EditText): Boolean {
        return etText.text.toString().trim { it <= ' ' }.length == 0
    }

    fun showConfirmAlert(context: Context, title: String, message: String): AlertDialog {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.action_confirm)) { dialog, which -> }
            .setNegativeButton(context.getString(R.string.action_cancel)) { dialog, which -> }
            .show()

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(context.resources.getColor(R.color.redPrimary))

        return alertDialog
    }
}