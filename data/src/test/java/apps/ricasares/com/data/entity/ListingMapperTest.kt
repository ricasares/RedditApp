package apps.ricasares.com.data.entity

import apps.ricasares.com.data.entity.mapper.ListingMapper
import apps.ricasares.com.data.factory.RedditResponseFactory
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by rush on 1/24/18.
 */
class ListingMapperTest {
    private var mapper: ListingMapper = ListingMapper()

    @Test
    fun testMapEntityToModel() {
        val redditResponse = RedditResponseFactory.makeRedditResponse(2)
        val listing = mapper.mapEntityToModel(redditResponse)

        assertThat(listing.entries.size, `is`(redditResponse.data.children.size))
        for (i in 0 until redditResponse.data.children.size) {
            var childrenResponse = redditResponse.data.children.get(i)
            var link = listing.entries[i]

            assertEquals(childrenResponse.data.title, link.title)
            assertEquals(childrenResponse.data.subreddit, link.subReddit)
            assertEquals(childrenResponse.data.thumbnail, link.preview.url)
            assertEquals(childrenResponse.data.score, link.points)
            assertEquals(childrenResponse.data.num_comments, link.commentCount)
            assertEquals(childrenResponse.data.created, link.date)
        }
    }
}