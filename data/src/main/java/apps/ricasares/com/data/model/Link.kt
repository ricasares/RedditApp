package apps.ricasares.com.data.model

/**
 * Created by rush on 11/17/17.
 */
class Link (
        val ups: Int,
        val downs: Int,
        val likes: Boolean,
        //val created: Long,
        val created_utc: Long,
        val author: String,
        val author_flair_css_class: String,
        val author_flair_text: String,
        val clicked: Boolean,
        val domain: String,
        val hidden: Boolean,
        val is_self: Boolean,
        val link_flair_css_class: String,
        val link_flair_text: String,
        val locked: Boolean,
        //val media: Object,
        //val media_embed: Object,
        val num_comments: Int,
        val over_18: Boolean,
        val permalink: String,
        val saved: Boolean,
        val score: Int,
        val selftext: String,
        val selftext_html: String,
        val subreddit: String,
        val subreddit_id: String,
        val thumbnail: String,
        val title: String,
        val url: String,
        //val edited: Long,
        val distinguished: String,
        val stickied: Boolean
)