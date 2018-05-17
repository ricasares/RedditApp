package apps.ricasares.com.data.repository.store

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.factory.DataFactory
import apps.ricasares.com.data.factory.RedditResponseFactory
import apps.ricasares.com.data.entity.RedditResponse
import apps.ricasares.com.data.repository.ListingMemoryDataStore
import com.example.android.architecture.blueprints.todoapp.any
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * Created by rush on 1/23/18.
 */
class ListingMemoryDataStoreTest {
    private lateinit var cache: ListingCache
    private lateinit var memoryStore: ListingMemoryDataStore

    @Before
    fun setUp() {
        cache = mock(ListingCache::class.java)
        memoryStore = ListingMemoryDataStore(cache)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testGetAllListings() {
        mockCacheGetAll("all", Flowable.just(RedditResponseFactory.makeRedditResponse(2)))
        val testObserver = memoryStore.getListings().test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValue({ redditResponse -> redditResponse.data.children.size == 2})
    }

    /*@Test(expected = UnsupportedOperationException::class)
    fun testGetListing() {
        val testObserver = memoryStore.getListings(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomInt()).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertError(UnsupportedOperationException::class.java)
    }*/

    @Test
    fun testSaveListings() {
        val redditReponse = mock(RedditResponse::class.java)
        mockCacheSaveResponse(Completable.fromAction { redditReponse })

        val testObserver = memoryStore.saveListings(redditReponse).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        Mockito.verify(cache).saveListings(any<RedditResponse>())
    }

    @Test
    fun testDeleteAll() {
        mockCacheClearListing(Completable.complete())
        val testObserver = memoryStore.deleteAllListings().test()
        testObserver.assertComplete()
        Mockito.verify(cache).clearListings()
    }

    private fun mockCacheGetAll(subreddit: String, single: Flowable<RedditResponse>) {
        Mockito.`when`(cache.getListings(subreddit)).thenReturn(single)
    }

    private fun mockCacheSaveResponse(completable: Completable) {
        Mockito.`when`(cache.saveListings(any<RedditResponse>())).thenReturn(completable)
    }

    private fun mockCacheClearListing(completable: Completable) {
        Mockito.`when`(cache.clearListings()).thenReturn(completable)
    }
}