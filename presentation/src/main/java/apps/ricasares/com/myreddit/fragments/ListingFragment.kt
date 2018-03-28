package apps.ricasares.com.myreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apps.ricasares.com.domain.interactor.browse.GetListingUseCase
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.presenter.ListingPresenter
import apps.ricasares.com.domain.repository.ListingRepository
import apps.ricasares.com.domain.schedulers.ObserveOn
import apps.ricasares.com.domain.schedulers.SubscribeOn
import apps.ricasares.com.domain.view.ListingView

import apps.ricasares.com.myreddit.R
import apps.ricasares.com.myreddit.RedditApplication
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListingFragment : Fragment(), ListingView {
    private val LOG_TAG: String = "ListingFragment"
    @Inject lateinit var listingRepository: ListingRepository
    @Inject lateinit var observeOn: ObserveOn
    @Inject lateinit var subscribeOn: SubscribeOn

    private lateinit var getListingUseCase: GetListingUseCase
    private lateinit var listingPresenter: ListingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getListingUseCase = GetListingUseCase(listingRepository, subscribeOn, observeOn)
        listingPresenter = ListingPresenter(getListingUseCase)
        listingPresenter.attachView(this)
        listingPresenter.loadListings("all", "new", "", 10)

        if (arguments != null) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (context?.applicationContext as RedditApplication).getApplicationComponent().inject(this)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * ListingView
     */
    override fun showLoading() {
        Log.i(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.i(LOG_TAG, "hideLoading")
    }

    override fun showListings(listings: Listing) {
        Log.i(LOG_TAG, listings.toString())
    }

    override fun showError(error: String) {
        Log.w(LOG_TAG, "showError $error")
    }

    companion object {
        val TAG = "ListingFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): ListingFragment {
            val fragment = ListingFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
