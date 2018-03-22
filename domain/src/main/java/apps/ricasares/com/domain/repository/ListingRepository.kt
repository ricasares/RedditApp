package apps.ricasares.com.domain.repository

import apps.ricasares.com.domain.model.Listing
import io.reactivex.Single

/**
 * Created by ricardo casarez on 11/22/17.
 * http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
 */
interface ListingRepository {
    fun getListings(subReddit: String, listing: String, after: String, limit: Int) : Single<Listing>
}