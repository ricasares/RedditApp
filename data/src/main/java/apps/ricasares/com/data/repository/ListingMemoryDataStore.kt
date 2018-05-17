package apps.ricasares.com.data.repository

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by ricardo casarez on 11/21/17.
 */
class ListingMemoryDataStore @Inject constructor(
        @param:Named("memory_cache") private val memoryCache: ListingCache) : ListingDataStore {

    override fun getListings(): Single<RedditResponse> {
        throw UnsupportedOperationException("unsupported operation")
    }

    override fun getListings(subReddit: String, listing: String, after: String, limit: Int): Single<RedditResponse> {
        return memoryCache.getListings(subReddit).singleOrError()
    }

    override fun saveListings(listings: RedditResponse): Completable {
        return memoryCache.saveListings(listings)
    }

    override fun deleteAllListings() : Completable {
        return memoryCache.clearListings()
    }
}