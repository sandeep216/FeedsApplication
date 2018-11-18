package com.sandeepsingh.feedsapplication.feature.pojos

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Sandeep on 11/17/18.
 *
 * This parcelable item maps the item of rows
 */
class FeedItem() : Parcelable {

    var title : String ?= ""
    var description : String ?= ""
    var imageHref : String ?= ""

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        description = parcel.readString()
        imageHref = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageHref)
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