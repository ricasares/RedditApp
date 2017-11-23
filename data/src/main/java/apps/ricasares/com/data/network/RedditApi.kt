package network

import apps.ricasares.com.data.model.RedditResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by rush on 11/17/17.
 */
interface RedditApi {
    @GET("/r/{subreddit}/{listing}/.json")
    fun getListing(@Path("subreddit") subreddit: String,
                   @Path("listing") listing: String,
                   @Query("after") after: String,
                   @Query("limit") limit: String) : Single<RedditResponse>

    @GET("/{listing}.json")
    fun getFrontPageListing(@Path("listing") listing: String,
                     @Query("after") after: String,
                     @Query("limit") limit: String) : Single<RedditResponse>
}