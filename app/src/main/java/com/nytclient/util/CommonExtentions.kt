package com.nytclient.util

import android.app.Activity
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nytclient.R

/**
 * Commonly used extension functions.
 * Created by Dogan Gulcan on 9/12/17.
 */

/**
 * Loads given image url into the ImageView via Glide.
 */
fun ImageView.loadUrl(url: String) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions()
                    .error(context.getDrawable(R.drawable.ic_broken_image_24dp)))
            .into(this)
}

/**
 * Opens url with [CustomTabsIntent] given [Activity] context.
 */
fun Activity.openUrlInCustomTab(url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
    customTabsIntent.launchUrl(this, Uri.parse(url))
}

