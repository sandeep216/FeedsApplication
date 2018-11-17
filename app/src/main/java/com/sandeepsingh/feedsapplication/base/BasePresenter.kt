package com.sandeepsingh.feedsapplication.base

/**
 * Created by Sandeep on 11/17/18.
 */

import java.lang.ref.WeakReference

open class BasePresenter<T>(view: T) {

    protected var mView: WeakReference<T>? = null

    protected fun setView(view: T) {
        mView = WeakReference(view)
    }

    @Throws(NullPointerException::class)
    protected fun getView(): T? {
        return if (mView != null)
            mView!!.get()
        else
            throw NullPointerException("View is unavailable")
    }

    protected open fun onDestroy() {
        mView = null
    }
}