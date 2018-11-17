package com.sandeepsingh.feedsapplication.feature.presenter

import com.sandeepsingh.feedsapplication.feature.IFeed
import com.sandeepsingh.feedsapplication.feature.model.FeedModel
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds
import com.sandeepsingh.githubuserauthenticationapp.base.BasePresenter

/**
 * Created by Sandeep on 11/17/18.
 */
class FeedPresenter(view : IFeed.PresenterToView) : BasePresenter<IFeed.PresenterToView>(view), IFeed.ModelToPresenter,IFeed.ViewToPresenter {

    private var model : FeedModel ?= null

    fun setModel(model: FeedModel){
        this.model = model
    }
    override fun loadData() {
        model!!.loadData()
    }

    override fun notifyDataSetChanged(feeds: Feeds) {
        if (getView()!= null) {
            getView()!!.notifyDataSetChanged(feeds)
        }
    }
}