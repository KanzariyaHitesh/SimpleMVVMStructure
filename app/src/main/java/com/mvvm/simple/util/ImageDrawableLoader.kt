package com.mvvm.simple.util

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mvvm.simple.R

@BindingAdapter(
    value = ["img:url", "img:drawable", "img:width", "img:height", "img:placeholder"],
    requireAll = false
)
fun loadImage(
    view: ImageView,
    url: String?,
    drawable: Int = R.drawable.ic_placeholder,
    width: Int,
    height: Int,
    placeholder: Int = R.drawable.ic_placeholder

) {
    Glide.with(view.context).load(url).override(
        width, height
    ).addListener(object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
            Log.e("Error", "Error-->Glide--." + e!!.localizedMessage)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable?>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            Log.e("onResourceReady", "onResourceReady-->GlideDrawable--")
            return false
        }
    })
        .placeholder(R.drawable.ic_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.IMMEDIATE)
        .error(R.drawable.ic_placeholder)
        .into(view)
}