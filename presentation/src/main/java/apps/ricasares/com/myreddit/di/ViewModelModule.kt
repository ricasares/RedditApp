package apps.ricasares.com.myreddit.di

import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.myreddit.listing.ListingsViewModelFactory
import dagger.Module

@Module
class ViewModelModule {
    fun providesListingViewModelFactory(listingUseCase: GetListingUseCase) : ListingsViewModelFactory {
        return ListingsViewModelFactory(listingUseCase)
    }
}