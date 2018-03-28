package apps.ricasares.com.data.repository

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Single
import network.RedditApi
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by ricardo casarez on 11/22/17.
 */
class ListingCloudDataStore @Inject constructor(
        private val redditApi: RedditApi,
        @param:Named("disk_cache") private val diskCache: ListingCache) : ListingDataStore {

    override fun getListings(): Single<RedditResponse> {
        throw UnsupportedOperationException()
    }

    override fun getListings(subReddit: String, listing: String, after: String, limit: Int): Single<RedditResponse> {
        return redditApi.getListing(subReddit, listing, after, limit.toString())
                .doOnSuccess{ response -> diskCache.saveListings(response).blockingAwait() }
    }

    override fun saveListings(listings: RedditResponse): Completable {
        throw UnsupportedOperationException()
    }

    override fun deleteAllListings() : Completable {
        throw UnsupportedOperationException()
    }
}