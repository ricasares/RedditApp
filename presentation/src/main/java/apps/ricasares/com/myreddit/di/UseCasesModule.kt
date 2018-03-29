package apps.ricasares.com.myreddit.di

import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.domain.repository.ListingRepository
import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import dagger.Module

/**
 * Created by ricardo casarez on 3/29/18.
 */
@Module
class UseCasesModule {

    fun provideGetListingUseCase(
            listingRepository: ListingRepository,
            subscribeOn: SubscribeOn,
            observeOn: ObserveOn) : GetListingUseCase = GetListingUseCase(listingRepository, subscribeOn, observeOn)
}