package apps.ricasares.com.domain.view

import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.mvp.View

/**
 * Created by ricardo casarez on 3/22/18.
 */
interface ListingView : View {
    fun showLoading()
    fun hideLoading()
    fun showListings(listings: Listing)
    fun showError(error: String)
}