package apps.ricasares.com.myreddit.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import apps.ricasares.com.domain.view.ListingView

import apps.ricasares.com.myreddit.R
import apps.ricasares.com.myreddit.RedditApplication
import apps.ricasares.com.myreddit.data.Resource
import apps.ricasares.com.myreddit.data.Status
import apps.ricasares.com.myreddit.listing.ListingAdapter
import apps.ricasares.com.myreddit.listing.ListingsViewModel
import apps.ricasares.com.myreddit.listing.ListingsViewModelFactory
import kotlinx.android.synthetic.main.fragment_listing.*
import javax.inject.Inject

/**
 * Created by ricardo casarez.
 * Use the [ListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListingFragment : Fragment(), ListingView {
    private val LOG_TAG = "ListingFragment"

    @Inject lateinit var listingViewModelFactory: ListingsViewModelFactory
    private lateinit var listingViewModel: ListingsViewModel
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

        listingViewModel = ViewModelProviders.of(this, listingViewModelFactory)
                .get(ListingsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listingAdapter = ListingAdapter(adapterPresenter)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listingAdapter

        listingViewModel.loadListings("all", "hot", null)
    }

    override fun onStart() {
        super.onStart()
        listingViewModel.getListings().observe(this, Observer<Resource<Listing>> {
            it?.let {
                when (it.status) {
                    Status.LOADING -> showLoading()
                    Status.SUCCESS -> showListings(it.data!!)
                    Status.ERROR -> showError(it.message!!)
                }
            }
        })
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
        Log.e(LOG_TAG, "showError $error")
        progressBar.visibility = View.INVISIBLE
    }
}
