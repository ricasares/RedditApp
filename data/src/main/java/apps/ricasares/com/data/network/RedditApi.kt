package network

import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by ricardo casarez on 11/17/17.
 */
interface RedditApi {
    @GET("/r/{subreddit}/{listing}/.json")
    fun getListings(@Path("subreddit") subreddit: String,
                   @Path("listing") listing: String,
                   @Query("after") after: String,
                   @Query("limit") limit: String) : Single<RedditResponse>

    @GET("")
    fun getListingsBefore(@Path("subreddit") subreddit: String,
                          @Path("listing") listing: String,
                          @Query("before") after: String,
                          @Query("limit") limit: String) : Single<RedditResponse>

    @GET("/{listing}.json")
    fun getFrontPageListing(@Path("listing") listing: String,
                     @Query("after") after: String,
                     @Query("limit") limit: String) : Single<RedditResponse>
}