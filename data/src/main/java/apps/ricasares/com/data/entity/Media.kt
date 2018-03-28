package apps.ricasares.com.data.entity

/**
 * Created by ricardo casarez on 1/25/18.
 */
data class Media (
        val reddit_video: RedditVideo,
        val num_comments: Int,
        val is_self: Boolean,
        val visited: Boolean,
        val mod_note: String,
        val is_video: Boolean,
        val distinguished: String
) {
    constructor() : this(
            RedditVideo(),
            -1,
            false,
            false,
            "",
            false,
            ""
    )
}