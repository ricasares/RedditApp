package apps.ricasares.com.myreddit.listing

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import apps.ricasares.com.domain.presenter.ListingItemPresenter
import apps.ricasares.com.myreddit.R

/**
 * Created by ricardo casarez on 3/28/18.
 */
class ListingAdapter(private val mPresenter: ListingItemPresenter) :
        RecyclerView.Adapter<ListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {

        val view = ListingViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_lisitng, parent, false))
        mPresenter.attachView(view)
        return view
    }

    override fun getItemCount(): Int = mPresenter.getItemCount()

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        mPresenter.onBindView(holder!!, position)
    }
}