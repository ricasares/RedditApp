package apps.ricasares.com.domain.interactor.browse

import apps.ricasares.com.domain.interactor.SingleUseCase
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.repository.ListingRepository
import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import io.reactivex.Single

/**
 * Created by ricardo casarez on 3/22/18.
 */
class GetListingUseCase constructor(
        private val listingRepository: ListingRepository,
        subscribeOn: SubscribeOn,
        observeOn: ObserveOn) : SingleUseCase<Listing, GetListingUseCase.Params>(subscribeOn, observeOn) {

    override public fun buildUseCaseObservable(params: Params?): Single<Listing> {
        return listingRepository.getListings(params!!.subReddit, params!!.listing, params!!.after, params!!.limit)
    }

    data class Params (
        var subReddit: String,
        var listing: String,
        var after: String,
        var limit: Int
    )
}