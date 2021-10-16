package com.example.films

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

fun Date.format(): String {
    return SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(this)
}

fun View.showSnackBar(
    text: String,
    actionText: String,
    length: Int = Snackbar.LENGTH_INDEFINITE,
    action: (View) -> Unit,
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun View.showSnackBar(
    text: String,
    length: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this, text, length).show()
}

fun View.showSnackBar(
    @StringRes text: Int,
    length: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this, text, length).show()
}

