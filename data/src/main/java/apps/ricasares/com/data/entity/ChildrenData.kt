package apps.ricasares.com.data.entity

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

/**
 * Created by ricardo casarez on 11/17/17.
 */
@Entity(tableName = "posts",
        indices = [(Index(value = ["subreddit"], unique = false))])
data class ChildrenData(
        var id: String,
        @PrimaryKey
        var name: String,
        var kind: String,
        var ups: Int,
        var downs: Int,
        var likes: Boolean?,
        var created: Long,
        var created_utc: Long,
        var approved_by: String?,
        var author: String,
        var author_flair_css_class: String?,
        var author_flair_text: String?,
        var banned_by: String?,
        var clicked: Boolean,
        var domain: String,
        var hidden: Boolean,
        @SerializedName("is_self")
        var self: Boolean,
        var link_flair_css_class: String?,
        var link_flair_text: String?,
        var locked: Boolean,
        @Ignore
        var media: Media?,
        @Ignore
        var media_embed: Media,
        var num_comments: Int,
        var over_18: Boolean,
        var permalink: String,
        var saved: Boolean,
        var score: Int,
        var selftext: String,
        var selftext_html: String?,
        @ColumnInfo(collate = ColumnInfo.NOCASE)
        var subreddit: String,
        var subreddit_id: String,
        var thumbnail: String,
        var title: String,
        var url: String,
        //val edited: Long, // could be boolean and long
        var distinguished: String?,
        var stickied: Boolean,
        @Ignore
        var preview: Preview?,
        var post_hint: String
) {
    constructor() : this(
            "",
            "",
            "",
            -1,
            -1,
            null,
            -1,
            -1,
            null,
            "",
            "",
            "",
            null,
            false,
            "",
            false,
            false,
            null,
            null,
            false,
            null,
            Media(),
            -1,
            false,
            "",
            false,
            -1,
            "",
            null,
            "",
            "",
            "",
            "",
            "",
            null,
            false,
            null,
            ""
    )
    var indexInResponse: Int = -1
}