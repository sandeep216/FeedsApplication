package com.sandeepsingh.feedsapplication

import com.nhaarman.mockito_kotlin.mock
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Created by Sandeep on 11/17/18.
 */
class UtilsUnitTest {

    lateinit var mPresenter : FeedPresenter

    @Before
    fun setup(){
        mPresenter = mock()
    }

    @Test
    fun testInternetConnectivity_IfContextIsProvided(){

    }
}