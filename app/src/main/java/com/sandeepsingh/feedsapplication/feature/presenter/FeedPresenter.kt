package com.sandeepsingh.feedsapplication.feature.presenter

import com.sandeepsingh.feedsapplication.base.BasePresenter
import com.sandeepsingh.feedsapplication.feature.IFeed
import com.sandeepsingh.feedsapplication.feature.model.FeedModel
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds

/**
 * Created by Sandeep on 11/17/18.
 *
 * Presenter class that works as communicating layer between the model/repo module and the activity.
 */
class FeedPresenter(view : IFeed.PresenterToView) : BasePresenter<IFeed.PresenterToView>(view), IFeed.ModelToPresenter,IFeed.ViewToPresenter {

    private var model : FeedModel ?= null

    init {
        setView(view)
    }

    /**
     * Method to set the instance of model to make use of model's function
     * @param model : Instance of FeedModel
     */
    fun setModel(model: FeedModel){
        this.model = model
    }

    /**
     * This method is invoked by the activity to fetch the data.
     */
    override fun loadData() {
        model!!.loadData()
    }

    /**
     * Function that notifies view after data being fetched with updated value.
     * @param feeds : Parcelable form of data being fetch from API.
     */
    override fun notifyDataSetChanged(feeds: Feeds?) {
        if (getView()!= null) {
            getView()!!.notifyDataSetChanged(feeds)
        }
    }

    /**
     * This method is invoked only when API failures or Exception have occurred.
     */
    override fun dataNotLoaded() {
        if (getView()!= null){
            getView()!!.dataNotLoaded()
        }
    }

    /**
     * This methods return data to the view while updating adapter
     */
    override fun getFeeds(): Feeds? {
       return model!!.getFeeds()
    }

    /**
     * This methods provides the data if previously fetched
     */
    override fun setFeeds(feeds: Feeds?) {
        model!!.setFeeds(feeds)
    }

}