package com.hqbd.weshare

import android.content.Context
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

open class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Log.d("GlideImageLoader", path as String)
        Glide.with(context!!).load(path).into(imageView!!)
    }

    override fun createImageView(context: Context?): ImageView {
        val imageView = ImageView(context)
        imageView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        imageView.setPadding(10, 0, 10, 0)
        return imageView
    }
}