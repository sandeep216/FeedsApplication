package com.sandeepsingh.feedsapplication.feature.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sandeepsingh.feedsapplication.R
import com.sandeepsingh.feedsapplication.base.extension.loadFromUrl
import com.sandeepsingh.feedsapplication.feature.pojos.FeedItem

/**
 * Created by Sandeep on 11/17/18.
 */
class FeedAdapter(private var listOfFeedsItems: ArrayList<FeedItem>, private var context: Context) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_child, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfFeedsItems.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBindViewHolder(listOfFeedsItems, position)
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvFeedTitle: TextView = itemView.findViewById(R.id.tv_title)
        private var tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        private var ivFeedImage: ImageView = itemView.findViewById(R.id.iv_feed_image)

        internal fun onBindViewHolder(listOfFeedsItems: ArrayList<FeedItem>, position: Int) {
            val feedItem = listOfFeedsItems[position]
            tvFeedTitle.text = feedItem.title
            tvDescription.text = feedItem.description
            if (feedItem.imageHref != null) {
                ivFeedImage.visibility = View.VISIBLE
                ivFeedImage.loadFromUrl(feedItem.imageHref)
            } else {
                ivFeedImage.visibility = View.GONE
            }
        }
    }

    fun notifyData(list: ArrayList<FeedItem>?) {
        this.listOfFeedsItems = list!!
        notifyDataSetChanged()
    }
}