package com.sandeepsingh.feedsapplication.feature.pojos

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

/**
 * Created by Sandeep on 11/17/18.
 */

class Feeds() : Parcelable {

    var title : String ?= ""
    var feedRows : ArrayList<FeedItem> ?= ArrayList()

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        parcel.readTypedList(feedRows,FeedItem.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(feedRows)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Feeds> {
        override fun createFromParcel(parcel: Parcel): Feeds {
            return Feeds(parcel)
        }

        override fun newArray(size: Int): Array<Feeds?> {
            return arrayOfNulls(size)
        }
    }
}