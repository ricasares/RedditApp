package apps.ricasares.com.data.cache

import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * ListingCache implementation for in memory cache.
 * Created by ricardo casarez on 1/24/18.
 */
class ListingMemoryCache : ListingCache {
    // todo implement db
    internal var redditResponse: RedditResponse = RedditResponse()

    override fun clearListings(): Completable {
        return Completable.fromAction {
            redditResponse = RedditResponse()
        }
    }

    override fun saveListings(response: RedditResponse): Completable {
        return Completable.fromAction {
            redditResponse = response
        }
    }

    override fun getListings(): Flowable<RedditResponse> {
        return Flowable.just(redditResponse)
    }

    override fun isCached(): Single<Boolean> {
        return Single.just(!redditResponse.data.children.isEmpty())
    }

    override fun setLastCacheTime(time: Long) {
        // not available
    }

    override fun isExpired(): Boolean {
        return false;
    }
}