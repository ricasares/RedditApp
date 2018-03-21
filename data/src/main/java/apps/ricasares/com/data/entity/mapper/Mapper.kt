package apps.ricasares.com.data.entity.mapper

/**
 * Created by ricardo casarez on 11/20/17.
 */
interface Mapper<A, B> {
    fun mapEntityToModel(source: A) : B
    //fun mapFromEntity(source: B) : A
}