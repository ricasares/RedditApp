package apps.ricasares.com.data.repository

import apps.ricasares.com.data.cache.ListingCache
import network.RedditApi
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by ricardo casarez on 11/22/17.
 */
class ListingDataStoreFactory @Inject constructor(
        @param:Named("disk_cache") private val diskCache: ListingCache,
        @param:Named("memory_cache") private val memoryCache: ListingCache,
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