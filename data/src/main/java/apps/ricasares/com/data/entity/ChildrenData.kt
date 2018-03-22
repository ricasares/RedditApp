package apps.ricasares.com.data.entity

/**
 * Created by ricardo casarez on 11/17/17.
 */
data class ChildrenData(
        val id: String,
        val name: String,
        val kind: String,
        val ups: Int,
        val downs: Int,
        val likes: Boolean,
        val created: Long,
        val created_utc: Long,
        val approved_by: String,
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
        val media: Media,
        val media_embed: Media,
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
        //val edited: Long, // could be boolean and long
        val distinguished: String,
        val stickied: Boolean
)