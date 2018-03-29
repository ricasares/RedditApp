package apps.ricasares.com.data.entity

import apps.ricasares.com.data.entity.preview.Images
import apps.ricasares.com.data.entity.preview.RedditVideoPreview

/**
 * Created by rush on 3/28/18.
 */
data class Preview (
        val images: List<Images>,
        val reddit_video_preview: RedditVideoPreview,
        val enabled: Boolean
) {
    constructor() : this(
            listOf<Images>(),
            RedditVideoPreview(),
            false
    )
}
