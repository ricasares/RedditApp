package apps.ricasares.com.data.network

import apps.ricasares.com.data.di.DaggerDataComponent
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.observers.TestObserver
import network.RedditApi
import org.junit.Test

/**
 * Created by ricardo on 11/17/17.
 */
class RdditApiTest {
    val redditApi: RedditApi = DaggerDataComponent.create().redditApi()

    @Test
    fun testListing() {
        val testObserver: TestObserver<RedditResponse> = redditApi.getListing("all","new", "", "10").test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }

    @Test
    fun testFrontPage() {
        val testObserver: TestObserver<RedditResponse> = redditApi.getFrontPageListing("hot","new", "10").test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }
}