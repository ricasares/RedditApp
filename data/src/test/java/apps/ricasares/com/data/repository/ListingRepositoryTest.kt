package apps.ricasares.com.data.repository

import apps.ricasares.com.data.factory.DataFactory.Companion.randomInt
import apps.ricasares.com.data.factory.DataFactory.Companion.randomString
import apps.ricasares.com.data.factory.RedditResponseFactory
import apps.ricasares.com.data.entity.ObjectData
import apps.ricasares.com.data.entity.RedditResponse
import apps.ricasares.com.data.entity.mapper.ListingMapper
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.repository.ListingRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.*

/**
 * Created by rush on 1/17/18.
 */
class ListingRepositoryTest {
    private val MEMORY_KIND = "memeory"
    private val DISK_KIND = "disk"
    private val CLOUD_KIND = "cloud"

    private lateinit var memory: ListingDataStore
    private lateinit var disk: ListingDataStore
    private lateinit var cloud: ListingDataStore

    private lateinit var factory: ListingDataStoreFactory
    private lateinit var mapper: ListingMapper

    private lateinit var repository: ListingRepository

    @Before
    fun setUp() {
        mapper = ListingMapper()

        memory = mock(ListingMemoryDataStore::class.java)
        disk = mock(ListingDiskDataStore::class.java)
        cloud = mock(ListingCloudDataStore::class.java)

        factory = mock(ListingDataStoreFactory::class.java)
        repository = ListingRepositoryImp(factory, mapper)

        `when`(factory.getMemoryDataStore()).thenReturn(memory)
        `when`(factory.getDiskDataStore()).thenReturn(disk)
        `when`(factory.getCloudDataStore()).thenReturn(cloud)
    }

    /**
     * Test get source data only from cloud.
     */
    @Test
    fun testGetNoMemoryNoDisk() {
        // mock sources
        mockMemoryDataStoreGetListing(Single.just(RedditResponse(MEMORY_KIND, mock(ObjectData::class.java))))

        mockDiskDataStoreGetListing(Single.just(RedditResponse(DISK_KIND, mock(ObjectData::class.java))))

        var redditReponse: RedditResponse = RedditResponseFactory.makeRedditResponse(5, CLOUD_KIND)
        mockCloudDataStoreGetListing(Single.just(redditReponse))

        // test completion
        var testObserver = repository.getListings(randomString(), randomString(), randomString(), randomInt()).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue({
            listing -> listing.entries.size == 5 && listing.kind.equals(CLOUD_KIND)
        })
    }

    /**
     * Test get source data only from disk
     */
    @Test
    fun testGetNoMemory() {
        mockMemoryDataStoreGetListing(Single.just(RedditResponse(MEMORY_KIND, mock(ObjectData::class.java))))
        val diskRedditResponse = RedditResponseFactory.makeRedditResponse(4, DISK_KIND)

        mockDiskDataStoreGetListing(Single.just(diskRedditResponse))
        val cloudRedditResponse = RedditResponseFactory.makeRedditResponse(5, CLOUD_KIND)

        mockCloudDataStoreGetListing(Single.just(cloudRedditResponse))

        var testObserver = repository.getListings(randomString(), randomString(), randomString(), randomInt()).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue({
            value -> value.entries.size == 4 && value.kind.equals(DISK_KIND)
        })
    }

    /**
     * Test get source data only from memory
     */
    @Test
    fun testGetAllSources() {
        val memoryRedditResposne = RedditResponseFactory.makeRedditResponse(1, MEMORY_KIND)
        mockMemoryDataStoreGetListing(Single.just(memoryRedditResposne))

        val diskRedditResponse = RedditResponseFactory.makeRedditResponse(2, DISK_KIND)
        mockDiskDataStoreGetListing(Single.just(diskRedditResponse))

        val cloudRedditResponse = RedditResponseFactory.makeRedditResponse(3, CLOUD_KIND)
        mockCloudDataStoreGetListing(Single.just(cloudRedditResponse))

        var testObserver = repository.getListings(randomString(), randomString(), randomString(), randomInt()).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue({
            value -> value.entries.size == 1 && value.kind.equals(MEMORY_KIND)
        })
    }

    /**
     * Test that if no source is available, we get default value
     */
    @Test
    fun testNoSource() {
        mockMemoryDataStoreGetListing(Single.just(RedditResponse(MEMORY_KIND, mock(ObjectData::class.java))))
        mockDiskDataStoreGetListing(Single.just(RedditResponse(DISK_KIND, mock(ObjectData::class.java))))
        mockCloudDataStoreGetListing(Single.just(RedditResponse(CLOUD_KIND, mock(ObjectData::class.java))))

        var testObserver = repository.getListings(randomString(), randomString(), randomString(), randomInt()).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue({
            value -> value.entries.isEmpty()
        })
    }

    private fun mockMemoryDataStoreGetListing(single: Single<RedditResponse>) {
        `when`(memory.getListings()).thenReturn(single)
    }

    private fun mockDiskDataStoreGetListing(single: Single<RedditResponse>) {
        `when`(disk.getListings()).thenReturn(single)
    }

    private fun mockCloudDataStoreGetListing(single: Single<RedditResponse>) {
        `when`(cloud.getListings(anyString(), anyString(), anyString(), anyInt())).thenReturn(single)
    }

    private fun mockMapper(redditResponse: RedditResponse, listing: Listing) {
        `when`(mapper.mapEntityToModel(redditResponse)).thenReturn(listing)
    }
}