package apps.ricasares.com.data.model

/**
 * Created by rush on 11/17/17.
 */
data class DataResponse(
        val before: String,
        val after: String,
        val modhash: String,
        val children: List<ChildrenResponse>
)