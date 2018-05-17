package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.model.PostHint
import apps.ricasares.com.domain.utils.RelativeDateFormat
import apps.ricasares.com.domain.view.ListingItemView
import apps.ricasares.com.mvp.StatelessPresenter
import java.util.*

/**
 * Created by ricardo casarez on 3/29/18.
 */
class ListingItemPresenter : StatelessPresenter<ListingItemView>() {
    private var mListing: Listing = Listing(listOf())

    fun setListing(listing: Listing) {
        mListing = listing
    }

    fun getItemCount() : Int = mListing.entries.size

    fun onBindView(view: ListingItemView, position: Int) {
        val entry = mListing.entries[position]
        view.setTitle(entry.title)
        view.setSubReddit(entry.subReddit)
        if (entry.hint == PostHint.IMAGE) {
            view.setPreviewImage(entry.preview.url, entry.preview.width, entry.preview.height)
        } else if (entry.hint == PostHint.RICH_VIDEO) {
            if (entry.isGif) {
                view.setPreviewGif(entry.preview.url)
            } else {
                view.setPreviewVideo(entry.preview.url)
            }
        } else if (entry.hint == PostHint.LINK) {
            view.setPreviewLink(entry.preview.url)
        }

        view.setPoints(entry.points.toString())
        view.setComments(entry.commentCount.toString())
        view.setDate(formatDate(entry.date * 1000))
    }

    private fun formatDate(timestamp: Long) : String {
        return RelativeDateFormat.toRelateve(timestamp, System.currentTimeMillis())
    }
}