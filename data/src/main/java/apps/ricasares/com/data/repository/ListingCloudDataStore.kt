package apps.ricasares.com.data.repository

import apps.ricasares.com.data.model.RedditResponse
import io.reactivex.Single
import network.RedditApi

/**
 * Created by rush on 11/22/17.
 */
class ListingCloudDataStore constructor(private val redditApi: RedditApi) : ListingDataStore {

    override fun getListings(): Single<RedditResponse> {
        throw UnsupportedOperationException()
    }

    override fun getListings(subReddit: String, listing: String, after: String): Single<RedditResponse> =
            redditApi.getListing(subReddit, listing, after, "15")
}