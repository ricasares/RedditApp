package apps.ricasares.com.data.repository

import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by ricardo casarez on 11/21/17.
 */
interface ListingDataStore {
    fun getListings() : Single<RedditResponse>
    fun getListings(subReddit: String, listing: String, after: String, limit: Int) : Single<RedditResponse>
    fun saveListings(listings: RedditResponse) : Completable
    fun deleteAllListings() : Completable
}