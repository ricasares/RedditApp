package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.factory.DataFactory
import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.view.ListingView
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import javax.xml.crypto.Data

/**
 * Created by rush on 3/26/18.
 */
class ListingPresneterTest {
    private val getListingUseCase: GetListingUseCase = mock(GetListingUseCase::class.java)
    private val listingView: ListingView = mock(ListingView::class.java)

    private lateinit var listingPresenter: ListingPresenter

    @Before
    fun setUp() {
        listingPresenter = ListingPresenter(getListingUseCase)
        listingPresenter.attachView(listingView)
        assertNotNull(listingPresenter.view)
    }

    @Test
    fun testLoadListing() {
        listingPresenter.loadListings(
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomInt())
        verify(listingPresenter.view)?.showLoading()
    }

    @Test
    fun testShowListings() {
        val listings: Listing = mock(Listing::class.java)
        listingPresenter.showListings(listings)
        verify(listingPresenter.view)?.showListings(listings)
    }

    @Test
    fun showError() {
        val error = DataFactory.randomString()
        listingPresenter.showError(error)
        verify(listingPresenter.view)?.showError(error)
    }
}