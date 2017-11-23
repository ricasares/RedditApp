package apps.ricasares.com.data.repository

import apps.ricasares.com.data.model.RedditResponse
import io.reactivex.Single

/**
 * Created by rush on 11/21/17.
 */
interface ListingDataStore {
    fun getListings() : Single<RedditResponse>
    fun getListings(subReddit: String, listing: String, after: String) : Single<RedditResponse>
}