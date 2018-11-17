package com.sandeepsingh.feedsapplication.feature

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Sandeep on 11/17/18.
 */
interface IFeedApi {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFactsFeeds() : Call<JsonElement>
}