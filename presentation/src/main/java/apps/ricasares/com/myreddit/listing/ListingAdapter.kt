package apps.ricasares.com.myreddit.listing

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apps.ricasares.com.domain.presenter.ListingItemPresenter
import apps.ricasares.com.domain.view.ListingItemView
import apps.ricasares.com.myreddit.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_lisitng.view.*

/**
 * Created by ricardo casarez on 3/28/18.
 */
class ListingAdapter(private val context: Context,
                     private val mPresenter: ListingItemPresenter) : RecyclerView.Adapter<ListingAdapter.ViewHolder>(), ListingItemView {

    init {
        mPresenter.attachView(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListingAdapter.ViewHolder =
            ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_lisitng, parent, false))

    override fun getItemCount(): Int = mPresenter.getItemCount()

    override fun onBindViewHolder(holder: ListingAdapter.ViewHolder?, position: Int) {
        val entry = mPresenter.getEntryAt(position)

        holder?.title?.text = entry?.title
        holder?.subReddit?.text = entry?.subReddit

        if (entry?.isGif!!) {
            Glide.with(context)
                    .asGif()
                    .load(entry?.thumb)
                    .into(holder!!.image)
        } else {
            Glide.with(context)
                    .asBitmap()
                    .load(entry?.thumb)
                    .into(holder!!.image)
        }
    }

    override fun notifyDataChanged() {
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.title!!
        val subReddit = view.subReddit!!
        val image = view.previewImage!!
    }
}