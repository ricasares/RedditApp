package apps.ricasares.com.data.entity.mapper

import apps.ricasares.com.data.entity.Children
import apps.ricasares.com.data.entity.ChildrenData
import apps.ricasares.com.data.entity.RedditResponse
import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.Listing

/**
 * Created by ricardo casarez on 11/20/17.
 */
open class ListingMapper : Mapper<RedditResponse, Listing> {
    override fun mapEntityToModel(redditResponse: RedditResponse) : Listing {
        var entries: MutableList<Entry> = mutableListOf()
        for (children: Children in redditResponse.data.children) {
            val link: ChildrenData = children.data
            entries.add(Entry(link.title, link.subreddit, link.thumbnail, link.score, link.num_comments, link.created_utc))
        }
        return Listing(redditResponse.kind, redditResponse.data.before, redditResponse.data.after, redditResponse.data.modhash, entries)
    }
}