package apps.ricasares.com.myreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.presenter.ListingItemPresenter
import apps.ricasares.com.domain.presenter.ListingPresenter
import apps.ricasares.com.domain.view.ListingView

import apps.ricasares.com.myreddit.R
import apps.ricasares.com.myreddit.RedditApplication
import apps.ricasares.com.myreddit.listing.ListingAdapter
import kotlinx.android.synthetic.main.fragment_listing.*
import javax.inject.Inject

/**
 * Created by ricardo casarez.
 * Use the [ListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListingFragment : Fragment(), ListingView {
    private val LOG_TAG = "ListingFragment"

    @Inject lateinit var listingPresenter: ListingPresenter
    private lateinit var listingAdapter: ListingAdapter
    private val adapterPresenter: ListingItemPresenter = ListingItemPresenter()

    companion object {
        val TAG = "ListingFragment"

        /**
         *
         */
        fun newInstance(): ListingFragment {
            val fragment = ListingFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (context?.applicationContext as RedditApplication).getApplicationComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listingPresenter.attachView(this)

        listingAdapter = ListingAdapter(adapterPresenter)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listingAdapter

        listingPresenter.loadListings("all", "hot", "", 10)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * ListingView
     */
    override fun showLoading() {
        Log.i(LOG_TAG, "showLoading")
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        Log.i(LOG_TAG, "hideLoading")
        progressBar.visibility = View.INVISIBLE
    }

    override fun showListings(listings: Listing) {
        Log.i(LOG_TAG, listings.toString())
        adapterPresenter.setListing(listings)
        listingAdapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        Log.w(LOG_TAG, "showError $error")
        progressBar.visibility = View.INVISIBLE
    }
}
