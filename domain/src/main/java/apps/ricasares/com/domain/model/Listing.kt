package apps.ricasares.com.domain.model

/**
 * Created by ricardo casarez on 12/28/17.
 */
data class Listing(
    val kind: String,
    val before: String?,
    val after: String,
    val modhash: String,
    val entries: List<Entry>
) {
    constructor() : this(
            "",
            null,
            "",
            "",
            listOf<Entry>()
    )
}