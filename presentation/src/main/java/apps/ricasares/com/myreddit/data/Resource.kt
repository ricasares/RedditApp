package apps.ricasares.com.myreddit.data

import apps.ricasares.com.myreddit.data.Status.*

/**
 * A generic class that describes a data with a status.
 */
data class Resource<out T> constructor(
        val status: Status,
        val data: T? = null,
        val message: String? = null) {

    companion object {
        fun <T> success(data: T) : Resource<T> = Resource(SUCCESS, data, null)
        fun <T> error(message: String, data: T?) : Resource<T> = Resource(ERROR, data, message)
        fun <T> loading(data: T?) : Resource<T> = Resource(LOADING, data, null)
    }
}