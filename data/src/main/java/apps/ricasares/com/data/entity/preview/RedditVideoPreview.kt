package apps.ricasares.com.data.entity.preview

/**
 * Created by rush on 3/28/18.
 */
data class RedditVideoPreview (
        val fallback_url: String,
        val height: Int,
        val width: Int,
        val scrubber_media_url: String,
        val dash_url: String,
        val duration: Int,
        val hls_url: String,
        val is_gif: Boolean,
        val transcoding_status: String
) {
    constructor() : this(
            "",
            -1,
            -1,
            "",
            "",
            -1,
            "",
            false,
            ""
    )
}