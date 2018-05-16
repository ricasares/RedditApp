package apps.ricasares.com.domain.presenter

import apps.ricasares.com.domain.factory.ListingFactory
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.model.PostHint
import apps.ricasares.com.domain.view.ListingItemView
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ListingItemPresenterTest {
    private val view: ListingItemView = mock(ListingItemView::class.java)
    private lateinit var presenter: ListingItemPresenter

    @Before
    fun setUp() {
        presenter = ListingItemPresenter()
        presenter.attachView(view)
    }

    @Test
    fun testSetData() {
        val listing = ListingFactory.makeListing(5)
        presenter.setListing(listing)
        assertThat(presenter.getItemCount(), `is`(5))
    }

    @Test
    fun testBindViewImage() {
        val listing = ListingFactory.makeListingOf(5, PostHint.IMAGE)
        presenter.setListing(listing)
        presenter.onBindView(view, 0)
        verify(view)?.setTitle(listing.entries[0].title)
        verify(view)?.setSubReddit(listing.entries[0].subReddit)
        //verify(view)?.setPreviewImage(listing.entries[0].thumb, )
        verify(view)?.setPoints(listing.entries[0].points.toString())
        verify(view)?.setComments(listing.entries[0].commentCount.toString())
        //verify(view)?.setDate("now")
    }
}