package com.sandeepsingh.feedsapplication.feature

import com.sandeepsingh.feedsapplication.feature.pojos.Feeds

/**
 * Created by Sandeep on 11/17/18.
 */
interface IFeed {

    interface ViewToPresenter{
        fun loadData()
    }

    interface PresenterToModel{
        fun loadData()
    }

    interface ModelToPresenter{
        fun notifyDataSetChanged(feeds: Feeds)
    }

    interface PresenterToView{
        fun notifyDataSetChanged(feeds: Feeds)
    }
}