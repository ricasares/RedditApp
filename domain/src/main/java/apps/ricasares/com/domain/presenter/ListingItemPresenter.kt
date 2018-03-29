package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.view.ListingItemView
import apps.ricasares.com.mvp.StatelessPresenter

/**
 * Created by ricardo casarez on 3/29/18.
 */
class ListingItemPresenter : StatelessPresenter<ListingItemView>() {
    private var mListing: Listing = Listing()

    fun setListing(listing: Listing) {
        mListing = listing
    }

    fun getItemCount() : Int = mListing!!.entries.size

    fun getEntryAt(position: Int) : Entry {
        return mListing.entries[position]
    }

    fun onBindView(view: ListingItemView, position: Int) {
        val entry = mListing.entries[position]
        view.setTitle(entry.title)
        view.setSubReddit(entry.subReddit)
        if (!entry.isGif) {
            view.setPreviewImage(entry.thumb)
        }

        view.setPoints(entry.points.toString())
        view.setComments(entry.commentCount.toString())
        view.setDate(entry.date.toString())
    }
}