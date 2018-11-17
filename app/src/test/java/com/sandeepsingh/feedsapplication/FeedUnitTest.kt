package com.sandeepsingh.feedsapplication

import com.google.gson.JsonElement
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sandeepsingh.feedsapplication.feature.IFeed
import com.sandeepsingh.feedsapplication.feature.IFeedApi
import com.sandeepsingh.feedsapplication.feature.model.FeedModel
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter
import org.junit.Before
import org.junit.Test
import retrofit2.Call

/**
 * Created by Sandeep on 11/17/18.
 */
class FeedUnitTest {

    private lateinit var feedPresenter: IFeed.ModelToPresenter
    private lateinit var feedModel: FeedModel
    private lateinit var mockedCalls: Call<Feeds>
    private lateinit var iRetrifitCalls : IFeedApi

    @Before
    fun setup(){
        feedPresenter = mock()
        feedModel = mock()
        mockedCalls = mock()
        iRetrifitCalls = mock()

        whenever(iRetrifitCalls.getFactsFeeds()).thenReturn(mockedCalls)
    }

    @Test
    fun fetchData(){
        feedModel = FeedModel(feedPresenter)
        feedModel.loadData()
        verify(feedPresenter).dataNotLoaded()
    }
}