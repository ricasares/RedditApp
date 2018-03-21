package apps.ricasares.com.data.entity

/**
 * Created by ricardo casarez on 11/17/17.
 */

data class ObjectData(
        val before: String,
        val after: String,
        val modhash: String,
        val whitelist_status: String,
        val children: List<Children>
)