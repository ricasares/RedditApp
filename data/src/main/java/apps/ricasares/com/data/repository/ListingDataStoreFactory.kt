package apps.ricasares.com.data.repository

import apps.ricasares.com.data.cache.ListingCache
import network.RedditApi

/**
 * Created by ricardo casarez on 11/22/17.
 */
class ListingDataStoreFactory constructor(
        private val diskCache: ListingCache,
        private val memoryCache: ListingCache,
        private val redditApi: RedditApi) {

    fun getCloudDataStore() : ListingDataStore {
        return ListingCloudDataStore(redditApi, diskCache)
    }
    fun getDiskDataStore() : ListingDataStore {
        return ListingDiskDataStore(diskCache, memoryCache)
    }
    fun getMemoryDataStore() : ListingDataStore {
        return ListingMemoryDataStore(memoryCache)
    }
}