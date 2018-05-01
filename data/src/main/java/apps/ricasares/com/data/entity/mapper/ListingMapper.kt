package apps.ricasares.com.data.entity.mapper

import apps.ricasares.com.data.entity.Children
import apps.ricasares.com.data.entity.ChildrenData
import apps.ricasares.com.data.entity.Preview
import apps.ricasares.com.data.entity.RedditResponse
import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.ImagePreview
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.model.PostHint

/**
 * Created by ricardo casarez on 11/20/17.
 */
open class ListingMapper : Mapper<RedditResponse, Listing> {
    override fun mapEntityToModel(source: RedditResponse) : Listing {
        var entries: MutableList<Entry> = mutableListOf()
        for (children: Children in source.data.children) {
            val link: ChildrenData = children.data
            // default thumbnail
            var thumb = link.thumbnail

            // try to get better preview image
            val preview: Preview? = link.preview
            var width = 0
            var height = 0
            if (preview != null && !preview.images.isEmpty()) {
                thumb = preview.images.last().source.url
                width = preview.images.last().source.width
                height = preview.images.last().source.height
            }
            val imagePreview = ImagePreview(thumb, width, height)

            val isGif = preview?.reddit_video_preview?.is_gif ?: false

            var postHint: PostHint? = PostHint.valueFor(link.post_hint)
            if (postHint == null) {
                postHint = PostHint.LINK
            }
            entries.add(Entry(link.title, link.subreddit, link.url, link.score, link.num_comments, link.created, isGif, postHint, imagePreview))
        }
        return Listing(source.kind, source.data.before, source.data.after, source.data.modhash, entries)
    }
}