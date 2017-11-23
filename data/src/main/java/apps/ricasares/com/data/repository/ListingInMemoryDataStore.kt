package apps.ricasares.com.data.repository

import apps.ricasares.com.data.model.RedditResponse
import io.reactivex.Single

/**
 * Created by rush on 11/21/17.
 */
class ListingInMemoryDataStore : ListingDataStore {

    private var redditResponse: RedditResponse? = null

    override fun getListings(): Single<RedditResponse> = Single.just(redditResponse)

    override fun getListings(subReddit: String, listing: String, after: String): Single<RedditResponse> {
        throw UnsupportedOperationException()
    }
}