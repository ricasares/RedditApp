package apps.ricasares.com.myreddit.di

import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.domain.presenter.ListingPresenter
import dagger.Module

/**
 * Created by ricardo casarez on 3/29/18.
 */
@Module
class PresentersModule {

    fun providesListingPresenter(getListingUseCase: GetListingUseCase) : ListingPresenter =
            ListingPresenter(getListingUseCase)
}