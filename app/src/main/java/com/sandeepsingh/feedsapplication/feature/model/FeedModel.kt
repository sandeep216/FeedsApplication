package com.sandeepsingh.feedsapplication.feature.model

import com.sandeepsingh.feedsapplication.feature.IFeed
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter

/**
 * Created by Sandeep on 11/17/18.
 */
class FeedModel(val presenter : FeedPresenter) : IFeed.PresenterToModel {

    override fun loadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}