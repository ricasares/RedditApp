package apps.ricasares.com.domain.model

/**
 * Created by rush on 12/28/17.
 */
data class Listing(
    val kind: String,
    val before: String,
    val after: String,
    val modhash: String,
    val entries: List<Entry>
)