package com.nytclient.ui.binding

import android.databinding.BindingAdapter
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nytclient.util.DateTimeUtils
import com.nytclient.util.loadUrl
import java.util.*


/**
 * Commonly used binding adapters.
 *
 * Created by Dogan Gulcan on 9/16/17.
 */

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    imageView.loadUrl(url)
}

@BindingAdapter("visibilityToggle")
fun visibilityToggle(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("nyt:date")
fun setRelativeDate(view: TextView, date: String) {
    view.text = DateTimeUtils.getInstance().getTimeStampFromDate(date)?.let {
        DateUtils.getRelativeTimeSpanString(it,
                Calendar.getInstance(TimeZone.getDefault()).timeInMillis,
                android.text.format.DateUtils.SECOND_IN_MILLIS)
    }
}


