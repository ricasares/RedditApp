package apps.ricasares.com.data.entity

/**
 * Created by ricardo casarez on 11/17/17.
 */

class RedditData(
        val before: String?,
        val after: String?,
        val children: List<Children>
) {
    constructor(children: List<Children>) : this(
            null,
            null,
            children
    )
}