package apps.ricasares.com.myreddit.listing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.myreddit.data.Resource
import io.reactivex.observers.DisposableSingleObserver

class ListingsViewModel(
        private val getListingsUseCase: GetListingUseCase) : ViewModel() {

    private val listings: MutableLiveData<Resource<Listing>> = MutableLiveData()

    override fun onCleared() {
        getListingsUseCase.dispose()
        super.onCleared()
    }

    fun loadListings(subreddit: String, listing: String, after: String? = null, limit: Int = 10) {
        listings.postValue(Resource.loading(null))
        getListingsUseCase.execute(ListingSingleObserver(), GetListingUseCase.Params(subreddit, listing,
                after ?: ""
                , limit))
    }

    fun getListings() : LiveData<Resource<Listing>> = listings

    inner class ListingSingleObserver : DisposableSingleObserver<Listing>() {
        override fun onError(e: Throwable) {
            listings.postValue(Resource.error(e.message.toString(), null))
        }

        override fun onSuccess(value: Listing) {
            listings.postValue(Resource.success(value))
        }
    }
}