package apps.ricasares.com.myreddit.listing

import android.content.res.Resources
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import apps.ricasares.com.domain.view.ListingItemView
import apps.ricasares.com.myreddit.R
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by ricardo casarez on 3/29/18.
 */
class ListingViewHolder : RecyclerView.ViewHolder, ListingItemView {

    @BindView(R.id.title) lateinit var title: TextView
    @BindView(R.id.subReddit) lateinit var subReddit: TextView
    @BindView(R.id.previewImage) lateinit var preview: ImageView
    @BindView(R.id.type) lateinit var mediaType: TextView
    @BindView(R.id.points) lateinit var points: TextView
    @BindView(R.id.comments) lateinit var comments: TextView
    @BindView(R.id.date) lateinit var date: TextView
    @BindView(R.id.previewProgress) lateinit var progress: ProgressBar
    private var view: View

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
        this.view = view
    }

    override fun setTitle(title: String) {
        this.title.text = title
    }

    override fun setSubReddit(subReddit: String) {
        this.subReddit.text =
                view.context.getString(R.string.sub_reddit_subtitle, subReddit)
    }

    override fun setPreviewImage(url: String, width: Int, height: Int) {
        if (width * height > 0) {
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val screenHeight = Resources.getSystem().displayMetrics.heightPixels
            val wRatio = screenWidth / width
            val hRatio = screenHeight / height

            preview.minimumHeight = wRatio * height
        }
        loadImageIntoView(url, preview)
        mediaType.text = "Image"
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

    override fun setPreviewGif(url: String) {
        loadImageIntoView(url, preview)
        mediaType.text = "Gif"
    }

    override fun setPreviewVideo(url: String) {
        loadImageIntoView(url, preview)
        mediaType.text = "Video"
    }

    override fun setPreviewLink(url: String) {
        loadImageIntoView(url, preview)
        mediaType.text = "Link"
    }

    private fun hideLoading() {
        progress.visibility = View.GONE
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    private fun loadImageIntoView(url: String, view: ImageView) {
        showLoading()

        Glide.with(itemView.context)
                .asBitmap()
                .load(url)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        hideLoading()
                        return false
                    }
                })
                .into(view)
    }
}