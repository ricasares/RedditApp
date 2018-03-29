package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.view.ListingItemView
import apps.ricasares.com.mvp.StatelessPresenter

/**
 * Created by rush on 3/29/18.
 */
class ListingItemPresenter : StatelessPresenter<ListingItemView>() {
    private var mListing: Listing = Listing()

    fun setListing(listing: Listing) {
        mListing = listing
        view?.notifyDataChanged()
    }

    fun getItemCount() : Int = mListing!!.entries.size

    fun getEntryAt(position: Int) : Entry {
        return mListing.entries[position]
    }

    fun onContentClick() {

    }
}