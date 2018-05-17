package apps.ricasares.com.data.cache

import apps.ricasares.com.data.factory.RedditResponseFactory
import junit.framework.Assert.*
import org.junit.Test

/**
 * Created by rush on 1/24/18.
 */
class ListingMemoryCacheTest {
    private var cache: ListingMemoryCache = ListingMemoryCache()

    @Test
    fun testClearListings() {
        var testObserver = cache.clearListings().test()
        testObserver.assertComplete()
        assertTrue(cache.redditResponse.data.children.isEmpty())
    }

    @Test
    fun testSaveListings() {
        val redditResponse = RedditResponseFactory.makeRedditResponse(10)
        var testObserver = cache.saveListings(redditResponse).test()
        testObserver.assertComplete()
        assertEquals(redditResponse, cache.redditResponse)
    }

    @Test
    fun testGetListings() {
        val redditResponse = RedditResponseFactory.makeRedditResponse(10)
        cache.saveListings(redditResponse).subscribe()
        var testObserver = cache.getListings("subreddit").test()
        testObserver.assertComplete()
        testObserver.assertValue(redditResponse)
    }

    @Test
    fun testIsCached() {
        cache.clearListings().subscribe()
        var testObserver = cache.isCached().test()
        testObserver.assertComplete()
        testObserver.assertValue(false)

        cache.saveListings(RedditResponseFactory.makeRedditResponse(2)).subscribe()
        testObserver = cache.isCached().test()
        testObserver.assertComplete()
        testObserver.assertValue(true)
    }

    @Test
    fun testIsExpired() {
        assertFalse(cache.isExpired())
    }
}