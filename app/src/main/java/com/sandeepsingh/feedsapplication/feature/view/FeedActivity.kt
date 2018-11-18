package com.sandeepsingh.feedsapplication.feature.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import com.sandeepsingh.feedsapplication.R
import com.sandeepsingh.feedsapplication.base.utils.Utils
import com.sandeepsingh.feedsapplication.feature.IFeed
import com.sandeepsingh.feedsapplication.feature.adapter.FeedAdapter
import com.sandeepsingh.feedsapplication.feature.model.FeedModel
import com.sandeepsingh.feedsapplication.feature.pojos.FeedItem
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.layout_pull_referesh.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by Sandeep on 11/17/18.
 *
 * View that shows feeds being fetched from the model.
 */
class FeedActivity : AppCompatActivity(), IFeed.PresenterToView {

    private lateinit var mPresenter: FeedPresenter
    private lateinit var mAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        ButterKnife.bind(this)
        setupMvp()
        initComponents()
        loadData()
    }

    /**
     * Function to initialise the MVP pattern for this feature.
     */
    private fun setupMvp() {
        mPresenter = FeedPresenter(this)
        val feedModel = FeedModel(mPresenter)
        mPresenter.setModel(feedModel)
    }

    /**
     * Function to initialise the view related components.
     */
    private fun initComponents() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        mAdapter = FeedAdapter(ArrayList(), this)
        recycler_view.adapter = mAdapter
        recycler_view.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        refresh_layout.setOnRefreshListener {
            loadData()
        }
    }

    /**
     * Function to set the toolbar title for this view
     */
    private fun setToolbar(headerTitle: String) {
        tv_header.text = headerTitle
    }

    /**
     * Method that invokes model's method through presenter to fetch updated feeds.
     */
    private fun loadData() {
        refresh_layout.isRefreshing = true
        if (Utils.haveNetworkConnection(this)) {
            componentsVisibility(true)
            mPresenter.loadData()
        } else {
           componentsVisibility(false)
        }
    }

    /**
     * Function that plays with visibility of components
     * @param isVisible : Boolean value to passed based on the (connectivity/no data) scenarios.
     */
    private fun componentsVisibility(isVisible : Boolean){
        if (isVisible){
            layout_no_internet.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
        } else {
            refresh_layout.isRefreshing = false
            layout_no_internet.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
        }
    }

    /**
     * Notifies adapter with new values of feeds fetched from API.
     * @param feeds : Updated feeds from the API
     */
    override fun notifyDataSetChanged(feeds: Feeds?) {
        refresh_layout.isRefreshing = false
        setToolbar(feeds?.title!!)
        mAdapter.notifyData(feeds.rows)
    }

    /**
     * Function called when there is no data or any exception occur while calling API.
     */
    override fun dataNotLoaded() {
        componentsVisibility(false)
    }
}
