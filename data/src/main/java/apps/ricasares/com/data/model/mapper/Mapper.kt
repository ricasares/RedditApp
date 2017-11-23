package apps.ricasares.com.data.model.mapper

/**
 * Created by rush on 11/20/17.
 */
interface Mapper<A, B> {
    fun mapToEntity(source: A) : B
    //fun mapFromEntity(source: B) : A
}