package apps.ricasares.com.myreddit.listing

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import apps.ricasares.com.domain.view.ListingItemView
import apps.ricasares.com.myreddit.R
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide

/**
 * Created by ricardo casarez on 3/29/18.
 */
class ListingViewHolder : RecyclerView.ViewHolder, ListingItemView {

    @BindView(R.id.title) lateinit var title: TextView
    @BindView(R.id.subReddit) lateinit var subReddit: TextView
    @BindView(R.id.previewImage) lateinit var preview: ImageView
    @BindView(R.id.points) lateinit var points: TextView
    @BindView(R.id.comments) lateinit var comments: TextView
    @BindView(R.id.date) lateinit var date: TextView

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
    }

    override fun setTitle(title: String) {
        this.title.text = title
    }

    override fun setSubReddit(subReddit: String) {
        this.subReddit.text = subReddit
    }

    override fun setPreviewImage(url: String) {
        Glide.with(itemView.context)
                .asBitmap()
                .load(url)
                .into(preview)
    }

    override fun setPoints(points: String) {
        this.points.text = points
    }

    override fun setComments(comments: String) {
        this.comments.text = comments
    }

    override fun setDate(date: String) {
        this.date.text = date
    }
}