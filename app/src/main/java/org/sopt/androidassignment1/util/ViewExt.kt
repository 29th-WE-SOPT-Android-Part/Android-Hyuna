package org.sopt.androidassignment1.util

import android.content.Context
import android.widget.Toast

object ViewExt{
    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

