package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.model.PostHint
import apps.ricasares.com.domain.utils.RelativedateFormat
import apps.ricasares.com.domain.view.ListingItemView
import apps.ricasares.com.mvp.StatelessPresenter
import java.lang.StringBuilder
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by ricardo casarez on 3/29/18.
 */
class ListingItemPresenter : StatelessPresenter<ListingItemView>() {
    private var mListing: Listing = Listing()

    fun setListing(listing: Listing) {
        mListing = listing
    }

    fun getItemCount() : Int = mListing.entries.size

    fun onBindView(view: ListingItemView, position: Int) {
        val entry = mListing.entries[position]
        view.setTitle(entry.title)
        view.setSubReddit(entry.subReddit)
        if (entry.hint == PostHint.IMAGE) {
            view.setPreviewImage(entry.thumb)
        } else if (entry.hint == PostHint.RICH_VIDEO) {
            if (entry.isGif) {
                view.setPreviewGif(entry.thumb)
            } else {
                view.setPreviewVideo(entry.thumb)
            }
        } else if (entry.hint == PostHint.LINK) {
            view.setPreviewLink(entry.thumb)
        }

        view.setPoints(entry.points.toString())
        view.setComments(entry.commentCount.toString())
        view.setDate(formatDate(entry.date * 1000))
    }

    private fun formatDate(timestamp: Long) : String {
        return RelativedateFormat.toRelative(Math.abs(Date().time - Date(timestamp).time))
    }
}