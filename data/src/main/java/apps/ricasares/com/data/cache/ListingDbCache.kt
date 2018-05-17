package apps.ricasares.com.data.cache

import apps.ricasares.com.data.cache.db.RedditDb
import apps.ricasares.com.data.entity.Children
import apps.ricasares.com.data.entity.RedditData
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * ListingCache implementation for data base persistence.
 * Created by ricardo Casarez on 3/21/18.
 */
class ListingDbCache @Inject constructor(private val db: RedditDb) : ListingCache {
    internal var redditResponse: RedditResponse = RedditResponse(RedditData(listOf()))

    override fun clearListings(): Completable {
        return Completable.fromAction {
            redditResponse = RedditResponse(RedditData(listOf()))
        }
    }

    override fun saveListings(response: RedditResponse): Completable {
        return Completable.fromAction {
            response.data.children.let { posts ->
                db.runInTransaction {
                    val items = posts.mapIndexed { index, child ->
                        child.data
                    }
                    db.posts().insert(items)
                }
            }
            redditResponse = response
        }
    }

    override fun getListings(subreddit: String): Flowable<RedditResponse> {
        db.posts().getBySubreddit(subreddit)
        return Flowable.just(redditResponse)
    }

    override fun isCached(): Single<Boolean> {
        return Single.just(!redditResponse.data.children.isEmpty())
    }

    override fun setLastCacheTime(time: Long) {
        // not available
    }

    override fun isExpired(): Boolean {
        return false
    }
}