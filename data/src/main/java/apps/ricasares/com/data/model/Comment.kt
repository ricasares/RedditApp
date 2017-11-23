package apps.ricasares.com.data.model

/**
 * Created by rush on 11/17/17.
 */
data class Comment(
        val ups: Int,
        val downs: Int,
        val likes: Boolean,
        val created: Long,
        val created_utc: Long,
        val approved_by: String,
        val author: String,
        val author_flair_css_class: String,
        val author_flair_text: String,
        val banned_by: String,
        val body: String,
        val body_html: String,
        //val edited: special,
        val gilded: Int,
        val link_author: String,
        val link_id: String,
        val link_title: String,
        val link_url: String,
        val num_reports: Int,
        val parent_id: String,
        //val replies: List<thing>
        val saved: Boolean,
        val score: Int,
        val score_hidden: Boolean,
        val subreddit: String,
        val subreddit_id: String,
        val distinguished: String
)