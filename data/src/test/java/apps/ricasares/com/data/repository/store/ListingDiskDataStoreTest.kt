package apps.ricasares.com.data.repository.store

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.factory.DataFactory.Companion.randomInt
import apps.ricasares.com.data.factory.DataFactory.Companion.randomString
import apps.ricasares.com.data.factory.RedditResponseFactory
import apps.ricasares.com.data.entity.RedditResponse
import apps.ricasares.com.data.repository.ListingDiskDataStore
import com.example.android.architecture.blueprints.todoapp.any
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by rush on 1/23/18.
 */
class ListingDiskDataStoreTest {
    private lateinit var cache: ListingCache
    private lateinit var memory: ListingCache
    private lateinit var diskStore: ListingDiskDataStore

    @Before
    fun setUp() {
        cache = mock(ListingCache::class.java)
        memory = mock(ListingCache::class.java)
        diskStore = ListingDiskDataStore(cache, memory)
    }

    @Test
    fun testGetAllListings() {
        mockCacheGetAll(Flowable.just(RedditResponseFactory.makeRedditResponse(2)))
        val testObserver = diskStore.getListings().test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue({ redditResponse -> redditResponse.data.children.size == 2})
    }

    /*@Test(expected = UnsupportedOperationException::class)
    fun testGetListing() {
        val testObserver = diskStore.getListings(randomString(), randomString(), randomString(), randomInt()).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertError(UnsupportedOperationException::class.java)
    }*/

    @Test
    fun testSaveListings() {
        val redditReponse = mock(RedditResponse::class.java)
        mockCacheSaveResponse(Completable.fromAction { redditReponse })
        mockMemorySaveResponse(Completable.complete())

        val testObserver = diskStore.saveListings(redditReponse).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        verify(cache).saveListings(any<RedditResponse>())
        verify(memory).saveListings(any<RedditResponse>())
    }

    @Test
    fun testDeleteAll() {
        mockCacheClearListing(Completable.complete())
        mockMemoryClearResponse(Completable.complete())

        val testObserver = diskStore.deleteAllListings().test()
        testObserver.assertComplete()
        verify(cache).clearListings()
        verify(memory).clearListings()
    }

    private fun mockCacheGetAll(single: Flowable<RedditResponse>) {
        `when`(cache.getListings()).thenReturn(single)
    }

    private fun mockCacheSaveResponse(completable: Completable) {
        `when`(cache.saveListings(any<RedditResponse>())).thenReturn(completable)
    }

    private fun mockCacheClearListing(completable: Completable) {
        `when`(cache.clearListings()).thenReturn(completable)
    }

    private fun mockMemorySaveResponse(completable: Completable) {
        `when`(memory.saveListings(any<RedditResponse>())).thenReturn(completable)
    }

    private fun mockMemoryClearResponse(completable: Completable) {
        `when`(memory.clearListings()).thenReturn(completable)
    }
}