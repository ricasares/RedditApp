package apps.ricasares.com.domain.interactor.browse

import apps.ricasares.com.domain.interactor.SingleUseCase
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.repository.ListingRepository
import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by ricardo casarez on 3/22/18.
 */
class GetListingUseCase @Inject constructor(
        private val listingRepository: ListingRepository,
        subscribeOn: SubscribeOn,
        observeOn: ObserveOn) : SingleUseCase<Listing, GetListingUseCase.Params>(subscribeOn, observeOn) {

    public override fun buildUseCaseObservable(params: Params?): Single<Listing> {
        return listingRepository.getListings(params!!.subReddit, params!!.listing, params!!.after, params!!.limit)
    }

    override fun execute(observer: DisposableSingleObserver<Listing>, params: Params?) {
        addDisposable(buildUseCaseObservable(params)
                .subscribeWith(observer))
    }

    data class Params (
        var subReddit: String,
        var listing: String,
        var after: String,
        var limit: Int
    )
}