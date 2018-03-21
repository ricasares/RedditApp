package apps.ricasares.com.data.repository

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by ricardo casarez on 11/21/17.
 */
class ListingMemoryDataStore constructor(
        private val memoryCache: ListingCache) : ListingDataStore {

    override fun getListings(): Single<RedditResponse> = memoryCache.getListings().singleOrError()

    override fun getListings(subReddit: String, listing: String, after: String, limit: Int): Single<RedditResponse> {
        throw UnsupportedOperationException()
    }

    override fun saveListings(listings: RedditResponse): Completable {
        return memoryCache.saveListings(listings)
    }

    override fun deleteAllListings() : Completable {
        return memoryCache.clearListings()
    }
}