package com.sandeepsingh.feedsapplication.feature.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.sandeepsingh.feedsapplication.R
import com.sandeepsingh.feedsapplication.feature.IFeed
import com.sandeepsingh.feedsapplication.feature.adapter.FeedAdapter
import com.sandeepsingh.feedsapplication.feature.model.FeedModel
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.layout_pull_referesh.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class FeedActivity : AppCompatActivity(), IFeed.PresenterToView {

    lateinit var mPresenter : FeedPresenter
    lateinit var mAdapter : FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        ButterKnife.bind(this)
        setupMvp()
        initComponents()
        loadData()
    }

    fun setupMvp(){
        mPresenter = FeedPresenter(this)
        val feedModel = FeedModel(mPresenter)
        mPresenter.setModel(feedModel)
    }

    fun initComponents(){
        recycler_view.layoutManager = LinearLayoutManager(this)
        mAdapter = FeedAdapter()
        recycler_view.adapter = mAdapter
    }

    fun setToolbar(headerTitle : String){
        tv_header.text = headerTitle
    }

    fun loadData(){
        refresh_layout.isRefreshing = true
        mPresenter.loadData()
    }

    override fun notifyDataSetChanged(feeds: Feeds) {
        refresh_layout.isRefreshing = false
        setToolbar(feeds.title!!)
    }
}
