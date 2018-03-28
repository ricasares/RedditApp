package apps.ricasares.com.data.repository

import apps.ricasares.com.data.entity.mapper.ListingMapper
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.repository.ListingRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ricardo casarez on 12/28/17.
 */
class ListingRepositoryImp @Inject constructor(
        private val dataStoreFactory: ListingDataStoreFactory,
        private val listingMapper: ListingMapper) : ListingRepository {

    private val emptyListing = Listing("", "", "", "", listOf())

    override fun getListings(subReddit: String, listing: String, after: String, limit: Int): Single<Listing> {
        return Single.concat(
                        dataStoreFactory.getMemoryDataStore().getListings(),
                        dataStoreFactory.getDiskDataStore().getListings(),
                        dataStoreFactory.getCloudDataStore().getListings(subReddit, listing, after, limit)
                ).filter {
                    response -> !response.data.children.isEmpty()
                }
                .map { it -> listingMapper.mapEntityToModel(it) }
                .first(emptyListing)
    }
}