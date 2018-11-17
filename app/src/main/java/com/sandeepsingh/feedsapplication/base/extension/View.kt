package com.sandeepsingh.feedsapplication.base.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Sandeep on 11/17/18.
 */

fun ImageView.loadFromUrl(url : String?){
    Glide.with(this.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}

fun ImageView.loadFromUrl(url: String?,radius : Int){
    Glide.with(this.context.applicationContext)
            .load(url)
            .apply(RequestOptions().circleCrop())
            .apply(RequestOptions().transform(RoundedCorners(radius)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}