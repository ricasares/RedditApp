package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.view.ListingView
import apps.ricasares.com.mvp.Presenter
import apps.ricasares.com.mvp.ViewState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by ricardo casarez on 3/22/18.
 */
class ListingPresenter @Inject constructor (
        private val getListingsUseCase: GetListingUseCase) : Presenter<ListingView, ListingPresenter.ListingViewState>() {

    fun loadListings(subreddit: String, listing: String, after: String, limit: Int) {
        view?.showLoading()
        getListingsUseCase.execute(ListingSingleObserver(), GetListingUseCase.Params(subreddit, listing, after, limit))
    }

    fun showListings(listings: Listing) {
        view?.hideLoading()
        view?.showListings(listings)
    }

    fun showError(error: String) {
        view?.showError(error)
    }

    inner class ListingSingleObserver : DisposableSingleObserver<Listing>() {
        override fun onError(e: Throwable) {
            view?.showError(e.message.toString())
        }

        override fun onSuccess(value: Listing) {
            view?.hideLoading()
            view?.showListings(value)
        }
    }

    class ListingViewState : ViewState {

    }
}