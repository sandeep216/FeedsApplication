package com.sandeepsingh.feedsapplication.feature.pojos

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.sandeepsingh.feedsapplication.base.RetrofitServiceGenerator
import java.util.ArrayList

/**
 * Created by Sandeep on 11/17/18.
 */

class Feeds() : Parcelable {

    var title: String? = ""
    var rows: ArrayList<FeedItem>? = ArrayList()

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        parcel.readTypedList(rows, FeedItem.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(rows)
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

    fun getUserList(jsonElement: JsonElement?): Feeds {
        if (null == jsonElement || jsonElement.isJsonNull)
            throw NullPointerException("Null tagListElement not supported")
        if (!jsonElement.isJsonObject)
            throw IllegalArgumentException("Provided JsonElement is not of type JsonArray")

        val listType = object : TypeToken<Feeds>() {}.type

        return RetrofitServiceGenerator.getGson().fromJson(jsonElement, listType)
    }
}