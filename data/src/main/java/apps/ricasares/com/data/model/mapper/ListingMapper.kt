package apps.ricasares.com.data.model.mapper

import apps.ricasares.com.data.model.Link
import apps.ricasares.com.data.model.RedditResponse
import apps.ricasares.com.domain.entity.Listing

/**
 * Created by rush on 11/20/17.
 */
open class ListingMapper() : Mapper<Link, Listing> {
    override fun mapToEntity(source: Link): Listing =
            Listing(source.title, source.subreddit, source.thumbnail, source.score, source.num_comments, source.created_utc)

    /*override fun mapFromEntity(source: Listing): Link {
        return Link();
    }*/
}