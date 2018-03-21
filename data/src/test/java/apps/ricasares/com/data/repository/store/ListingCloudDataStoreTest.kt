package apps.ricasares.com.data.repository.store

import apps.ricasares.com.data.cache.ListingCache
import apps.ricasares.com.data.entity.RedditResponse
import apps.ricasares.com.data.repository.ListingCloudDataStore
import io.reactivex.Single
import network.RedditApi
import org.junit.Before
import org.junit.Test
import com.example.android.architecture.blueprints.todoapp.any
import io.reactivex.Completable
import org.mockito.Mockito.*

/**
 * Created by ricardo casarez on 1/23/18.
 */
class ListingCloudDataStoreTest {
    private lateinit var cloudStore: ListingCloudDataStore
    private lateinit var redditApi: RedditApi
    private lateinit var cache: ListingCache

    @Before
    fun setUp() {
        redditApi = mock(RedditApi::class.java)
        cache = mock(ListingCache::class.java)
        cloudStore = ListingCloudDataStore(redditApi, cache)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testGetAllListing() {
        var testObserver = cloudStore.getListings().test()
        testObserver.assertError(UnsupportedOperationException::class.java)
    }

    @Test
    fun testGetListing() {
        mockRedditApiGetListing(Single.just(mock(RedditResponse::class.java)))
        mockCacheSave(Completable.complete())

        val testObserver = cloudStore.getListings(anyString(), anyString(), anyString(), anyInt()).test()
        testObserver.assertComplete()
        verify(cache).saveListings(any<RedditResponse>())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testSaveListing() {
        val testObserver = cloudStore.saveListings(mock(RedditResponse::class.java)).test()
        testObserver.assertError(UnsupportedOperationException::class.java)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testDeleteAllListing() {
        val testObserver = cloudStore.deleteAllListings().test()
        testObserver.assertError(UnsupportedOperationException::class.java)
    }

    private fun mockRedditApiGetListing(single: Single<RedditResponse>) {
        `when`(redditApi.getListing(
                anyString(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(single)
    }

    private fun mockCacheSave(completable: Completable) {
        `when`(cache.saveListings(any<RedditResponse>())).thenReturn(completable)
    }
}