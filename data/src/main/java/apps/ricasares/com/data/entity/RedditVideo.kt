package apps.ricasares.com.data.entity

/**
 * Created by ricardo casarez on 1/25/18.
 */
data class RedditVideo (
        val fallback_url: String,
        val height: Int,
        val width: Int,
        val scrubber_media_url: String,
        val dash_url: String,
        val duration: Int,
        val hls_url: String,
        val is_gif: Boolean,
        val transcoding_status: String
)