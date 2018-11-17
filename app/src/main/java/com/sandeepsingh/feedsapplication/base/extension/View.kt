package com.sandeepsingh.feedsapplication.base.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sandeepsingh.feedsapplication.R

/**
 * Created by Sandeep on 11/17/18.
 *
 * This contains all the extension function related to views.
 */


/**
 * Extension function to fetch image from url using Glide.
 * @param url : Provide URL of image
 */
fun ImageView.loadFromUrl(url : String?){
        Glide.with(this.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions().error(R.drawable.broken_image))
            .into(this)
}

/**
 * Extension function to fetch image from url using Glide.
 * @param url : Provide URL of image
 * @param radius : Use for rounded images
 */
fun ImageView.loadFromUrl(url: String?,radius : Int){
    Glide.with(this.context.applicationContext)
            .load(url)
            .apply(RequestOptions().circleCrop())
            .apply(RequestOptions().transform(RoundedCorners(radius)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}