package com.levgeogr.backendlessresearch.ui.account

import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.levgeogr.backendlessresearch.R

@BindingAdapter("app:loginProgressVisible")
fun loginProgressVisible(view: ProgressBar, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:buttonTextHandler")
fun buttonTextHandler(view: Button, isAccountExists: Boolean) {
    view.setText(
        if (isAccountExists)
            R.string.login
        else
            R.string.registration
    )
}