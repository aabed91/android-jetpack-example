package com.firstcode.interviewapp.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("setButtonVisibility")
fun setButtonVisibility(view: View, showProgress: LiveData<Boolean>?) {
    if (showProgress?.value!!) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}


@BindingAdapter("setProgressVisibility")
fun setProgressVisibility(view: View, showProgress: LiveData<Boolean>?) {
    if (showProgress?.value != null) {
        if (showProgress?.value!!) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}
