package apps.ricasares.com.domain.model

/**
 * Created by rush on 11/20/17.
 */
data class Entry(
        val title: String,
        val subReddit: String,
        val thumb: String,
        val points: Int,
        val commentCount: Int,
        val date: Long
)