package apps.ricasares.com.data.repository

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.entity.RedditResponse
import junit.framework.Assert.assertTrue
import network.RedditApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock



/**
 * Created by rush on 12/18/17.
 */
class ListingDataStoreFactoryTest {
    private lateinit var diskCache: ListingCache
    private lateinit var memoryCache: ListingCache
    private lateinit var redditApi: RedditApi
    private lateinit var listingDataStoreFactory: ListingDataStoreFactory

    @Before
    fun setUp() {
        diskCache = mock(ListingCache::class.java)
        memoryCache = mock(ListingCache::class.java)
        redditApi = mock(RedditApi::class.java)
        listingDataStoreFactory = ListingDataStoreFactory(diskCache, memoryCache, redditApi)
    }

    @Test
    fun testGetCloudDataStore() {
        var cloudDataStore: ListingDataStore = listingDataStoreFactory.getCloudDataStore()
        assertTrue(cloudDataStore is ListingCloudDataStore)
    }

    @Test
    fun testGetDiskDataStore() {
        var diskDataStore: ListingDataStore = listingDataStoreFactory.getDiskDataStore()
        assertTrue(diskDataStore is ListingDiskDataStore)
    }

    @Test
    fun testGetMemoryDataStore() {
        var memoryDataStore: ListingDataStore = listingDataStoreFactory.getMemoryDataStore()
        assertTrue(memoryDataStore is ListingMemoryDataStore)
    }
}