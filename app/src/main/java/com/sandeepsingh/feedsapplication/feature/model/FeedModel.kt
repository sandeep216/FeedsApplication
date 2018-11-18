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
class FeedModel(val presenter: IFeed.ModelToPresenter) : IFeed.PresenterToModel {

    private var feeds: Feeds? = null
    /**
     * Function first checks for last instance of data, if found null then fetches the data from the API
     * and provides updated value.
     * This method is being invoked by the activity through presenter.
     */
    override fun loadData() {
        try {
            if (feeds != null) {
                presenter.notifyDataSetChanged(feeds)
            } else {
                val iFeedApi = Utils.getRetrofitInstance(URL.FACTS_API).create(IFeedApi::class.java)
                val call = iFeedApi.getFactsFeeds()
                val callback = object : Callback<Feeds> {
                    override fun onFailure(call: Call<Feeds>, t: Throwable) {
                        presenter.dataNotLoaded()
                    }

                    override fun onResponse(call: Call<Feeds>, response: Response<Feeds>) {
                        if (response.isSuccessful) {
                            feeds = response.body()!!
                            presenter.notifyDataSetChanged(feeds)
                        } else {
                            presenter.dataNotLoaded()
                        }
                    }

                }

                call.enqueue(callback)
            }
        } catch (e: Exception) {
            presenter.dataNotLoaded()
        }
    }

    /**
     * Saving the last instance of parcelable data
     * @param feeds : Last instance of data while saving state
     */
    override fun setFeeds(feeds: Feeds?) {
        this.feeds = feeds
    }

    /**
     * Function that returns last instance or updated value for the adapter to the view
     * @return feeds
     */
    override fun getFeeds(): Feeds? {
        return feeds
    }

}