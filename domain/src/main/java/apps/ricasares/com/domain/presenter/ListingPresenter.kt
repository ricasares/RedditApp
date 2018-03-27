package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.view.ListingView
import apps.ricasares.com.mvp.Presenter
import apps.ricasares.com.mvp.ViewState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by ricardo on 3/22/18.
 */
class ListingPresenter (
        private val getListingsUseCase: GetListingUseCase) : Presenter<ListingView, ListingPresenter.ListingViewState>() {

    private val listingObserver = object : DisposableSingleObserver<Listing>() {
        override fun onSuccess(listing: Listing?) {
            view?.hideLoading()
            view?.showListings(listing!!)
        }

        override fun onError(e: Throwable?) {
            view?.hideLoading()
            showError(e.toString())
        }
    }

    fun loadListings(subreddit: String, listing: String, after: String, limit: Int) {
        view?.showLoading()
        getListingsUseCase.execute(listingObserver, GetListingUseCase.Params(subreddit, listing, after, limit))
    }

    fun showListings(listings: Listing) {
        view?.hideLoading()
        view?.showListings(listings)
    }

    fun showError(error: String) {
        view?.showError(error)
    }

    class ListingViewState : ViewState {

    }
}