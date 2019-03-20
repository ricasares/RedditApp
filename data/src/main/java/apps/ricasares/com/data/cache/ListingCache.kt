package apps.ricasares.com.data.cache

import apps.ricasares.com.data.entity.ChildrenData
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by ricardo casarez on 11/21/17.
 */
interface ListingCache {
    /**
     * Clear all listings from cache.
     */
    fun clearListings() : Completable

    /**
     * Saves the response into cache.
     */
    fun saveListings(data: List<ChildrenData>) : Completable

    /**
     * Gets the cached response.
     */
    fun getListings() : Single<List<ChildrenData>>

    /**
     * Gets the cached response.
     */
    fun getListings(subbReddit: String) : Single<List<ChildrenData>>

    /**
     * Determines if cache data is available.
     */
    fun isCached() : Single<Boolean>

    /**
     * Sets date for cache.
     */
    fun setLastCacheTime(time: Long)

    /**
     * Determines if cache is expired.
     */
    fun isExpired() : Boolean
}