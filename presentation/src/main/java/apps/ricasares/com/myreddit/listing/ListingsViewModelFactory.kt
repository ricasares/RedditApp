package apps.ricasares.com.myreddit.listing

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import javax.inject.Inject

class ListingsViewModelFactory @Inject constructor(
        private val listingUseCase: GetListingUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListingsViewModel::class.java)) {
            return ListingsViewModel(listingUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}