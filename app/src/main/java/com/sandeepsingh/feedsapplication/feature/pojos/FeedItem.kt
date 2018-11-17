package com.sandeepsingh.feedsapplication.feature.pojos

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Sandeep on 11/17/18.
 */
class FeedItem() : Parcelable {

    var feedTitle : String ?= ""
    var feedDescription : String ?= ""
    var feedImage : String ?= ""

    constructor(parcel: Parcel) : this() {
        feedTitle = parcel.readString()
        feedDescription = parcel.readString()
        feedImage = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(feedTitle)
        parcel.writeString(feedDescription)
        parcel.writeString(feedImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeedItem> {
        override fun createFromParcel(parcel: Parcel): FeedItem {
            return FeedItem(parcel)
        }

        override fun newArray(size: Int): Array<FeedItem?> {
            return arrayOfNulls(size)
        }
    }
}