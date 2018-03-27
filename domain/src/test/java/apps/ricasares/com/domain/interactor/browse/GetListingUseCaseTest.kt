package apps.ricasares.com.domain.interactor.browse

import apps.ricasares.com.domain.factory.ListingFactory
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.repository.ListingRepository
import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import apps.ricasares.com.domain.factory.ParamsFactory
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

/**
 * Created by ricardo casarez on 3/22/18.
 */
class GetListingUseCaseTest {
    private lateinit var getListingUseCase: GetListingUseCase
    // dependencies
    private val listingRepository: ListingRepository = mock(ListingRepository::class.java)
    private val subscribeOn: SubscribeOn = mock(SubscribeOn::class.java)
    private val observeOn: ObserveOn = mock(ObserveOn::class.java)

    @Before
    fun setUp() {
        getListingUseCase = GetListingUseCase(listingRepository, subscribeOn, observeOn)
    }

    @Test
    fun testbuildUseCaseCallsRepoParams() {
        getListingUseCase.buildUseCaseObservable(ParamsFactory.getListingsParams())
        verify(listingRepository).getListings(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())
    }

    @Test
    fun buildUseCaseCompletes() {
        mockGetListing(Single.just(ListingFactory.makeListing(2)))
        val testObserver: TestObserver<Listing> = getListingUseCase.buildUseCaseObservable(ParamsFactory.getListingsParams()).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseReturnsData() {
        val listing = ListingFactory.makeListing(3);
        mockGetListing(Single.just(listing))
        val testObserver: TestObserver<Listing> = getListingUseCase.buildUseCaseObservable(ParamsFactory.getListingsParams()).test()
        testObserver.assertValue(listing)
    }

    private fun mockGetListing(single: Single<Listing>) {
        `when`(listingRepository.getListings(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
                .thenReturn(single)
    }
}