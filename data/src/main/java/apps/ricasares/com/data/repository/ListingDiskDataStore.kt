package apps.ricasares.com.data.repository

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by ricardo casarez on 12/18/17.
 */
class ListingDiskDataStore constructor(
        private val diskCache: ListingCache,
        private val memoryCache: ListingCache) : ListingDataStore {

    override fun getListings(): Single<RedditResponse> {
        return diskCache.getListings()
                .singleOrError()
    }

    override fun getListings(subReddit: String, listing: String, after: String, limit: Int): Single<RedditResponse> {
        return diskCache.getListings()
                .singleOrError()
    }

    override fun saveListings(listings: RedditResponse): Completable {
        return diskCache.saveListings(listings)
                .doOnComplete {
                    memoryCache.saveListings(listings).blockingAwait()
                }
    }

    override fun deleteAllListings() : Completable {
        return diskCache.clearListings()
                .doOnComplete {
                    memoryCache.clearListings().blockingAwait()
                }
    }
}