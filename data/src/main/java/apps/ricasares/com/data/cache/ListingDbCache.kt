package apps.ricasares.com.data.cache

import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * ListingCache implementation for data base persistence.
 * Created by ricardo Casarez on 3/21/18.
 */
class ListingDbCache : ListingCache {
    internal var redditResponse: RedditResponse? = null

    override fun clearListings(): Completable {
        return Completable.fromAction {
            redditResponse = null
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
        return Single.just(redditResponse != null)
    }

    override fun setLastCacheTime(time: Long) {
        // not available
    }

    override fun isExpired(): Boolean {
        return false
    }
}