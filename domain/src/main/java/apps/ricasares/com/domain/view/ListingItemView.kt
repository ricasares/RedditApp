package apps.ricasares.com.domain.view

import apps.ricasares.com.mvp.View

/**
 * Created by ricardo casarez on 3/29/18.
 */
interface ListingItemView : View {
    fun setTitle(title: String)
    fun setSubReddit(subReddit: String)
    fun setPreviewImage(url: String)
    fun setPoints(points: String)
    fun setComments(comments: String)
    fun setDate(date: String)
}