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
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.layout_pull_referesh.*
import kotlinx.android.synthetic.main.layout_toolbar.*

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

    private fun setupMvp() {
        mPresenter = FeedPresenter(this)
        val feedModel = FeedModel(mPresenter)
        mPresenter.setModel(feedModel)
    }

    private fun initComponents() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        mAdapter = FeedAdapter(ArrayList(), this)
        recycler_view.adapter = mAdapter
        recycler_view.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        refresh_layout.setOnRefreshListener {
            loadData()
        }
    }

    private fun setToolbar(headerTitle: String) {
        tv_header.text = headerTitle
    }

    private fun loadData() {
        refresh_layout.isRefreshing = true
        if (Utils.haveNetworkConnection(this)) {
            componentsVisibility(true)
            mPresenter.loadData()
        } else {
           componentsVisibility(false)
        }
    }

    private fun componentsVisibility(isVisible : Boolean){
        refresh_layout.isRefreshing = false
        if (isVisible){
            layout_no_internet.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
        } else {
            layout_no_internet.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
        }
    }

    override fun notifyDataSetChanged(feeds: Feeds?) {
        refresh_layout.isRefreshing = false
        setToolbar(feeds?.title!!)
        mAdapter.notifyData(feeds.rows)
    }

    override fun dataNotLoaded() {
        componentsVisibility(false)
    }
}
