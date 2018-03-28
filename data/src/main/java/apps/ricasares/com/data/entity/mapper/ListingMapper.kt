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
    override fun mapEntityToModel(source: RedditResponse) : Listing {
        var entries: MutableList<Entry> = mutableListOf()
        for (children: Children in source.data.children) {
            val link: ChildrenData = children.data
            entries.add(Entry(link.title, link.subreddit, link.thumbnail, link.score, link.num_comments, link.created_utc))
        }
        return Listing(source.kind, source.data.before, source.data.after, source.data.modhash, entries)
    }
}