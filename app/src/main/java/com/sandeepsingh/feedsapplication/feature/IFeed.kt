package com.sandeepsingh.feedsapplication.feature

import com.sandeepsingh.feedsapplication.feature.pojos.Feeds

/**
 * Created by Sandeep on 11/17/18.
 */
interface IFeed {

    interface ViewToPresenter{
        fun loadData()
        fun getFeeds() : Feeds?
        fun setFeeds(feeds: Feeds?)
    }

    interface PresenterToModel{
        fun loadData()
        fun getFeeds() : Feeds?
        fun setFeeds(feeds: Feeds?)
    }

    interface ModelToPresenter{
        fun notifyDataSetChanged(feeds: Feeds?)
        fun dataNotLoaded()
    }

    interface PresenterToView{
        fun notifyDataSetChanged(feeds: Feeds?)
        fun dataNotLoaded()
    }
}