package com.sandeepsingh.feedsapplication.feature.model

import com.sandeepsingh.feedsapplication.base.constants.URL
import com.sandeepsingh.feedsapplication.base.utils.Utils
import com.sandeepsingh.feedsapplication.feature.IFeed
import com.sandeepsingh.feedsapplication.feature.IFeedApi
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Sandeep on 11/17/18.
 */
class FeedModel(val presenter : FeedPresenter) : IFeed.PresenterToModel {

    override fun loadData() {
        try{
            val iFeedApi = Utils.getRetrofitInstance(URL.FACTS_API).create(IFeedApi :: class.java)
            val call = iFeedApi.getFactsFeeds()
            val callback = object : Callback<Feeds>{
                override fun onFailure(call: Call<Feeds>, t: Throwable) {

                }

                override fun onResponse(call: Call<Feeds>, response: Response<Feeds>) {
                    if (response.isSuccessful){
                        presenter.notifyDataSetChanged(response.body())
                    }
                }

            }

            call.enqueue(callback)
        } catch (e : Exception){

        }
    }
}