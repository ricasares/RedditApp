package apps.ricasares.com.data.cache

import apps.ricasares.com.data.model.RedditResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by rush on 11/21/17.
 */
interface ListingCache {
    fun clearListings() : Completable

    fun saveListings(response: RedditResponse) : Completable

    fun getListings() : Flowable<RedditResponse>

    fun isCached() : Single<Boolean>

    fun setLastCacheTime(time: Long)

    fun isExpired() : Boolean
}