package apps.ricasares.com.data.entity

/**
 * Created by ricardo casarez on 11/17/17.
 */
data class RedditResponse(
        val kind: String,
        val data: ObjectData
) {
    constructor() : this(
            "",
            ObjectData()
    )
}