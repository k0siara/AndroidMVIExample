package com.patrykkosieradzki.androidmviexample.utils

import android.view.View
import android.widget.Spinner
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun View.setOnClick(action: () -> Unit) {
    setOnClickListener { action.invoke() }
}

@BindingAdapter("visibleInvisible")
fun View.setVisibleInvisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("visibleGone")
fun View.setVisibleGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("selection")
fun Spinner.setSelection(selection: Int) {
    setSelection(selection)
}
