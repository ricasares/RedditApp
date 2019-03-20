package apps.ricasares.com.data.cache

import apps.ricasares.com.data.cache.db.RedditDb
import apps.ricasares.com.data.entity.Children
import apps.ricasares.com.data.entity.ChildrenData
import apps.ricasares.com.data.entity.RedditData
import apps.ricasares.com.data.entity.RedditResponse
import apps.ricasares.com.data.entity.mapper.ChildrenMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * ListingCache implementation for data base persistence.
 * Created by ricardo Casarez on 3/21/18.
 */
class ListingDbCache @Inject constructor(private val db: RedditDb) : ListingCache {

    override fun clearListings(): Completable {
        return Completable.fromAction {
            db.posts().deleteAll()
        }
    }

    override fun saveListings(response: List<ChildrenData>): Completable {
        return Completable.fromAction {
            db.posts().insert(response)
        }
    }

    override fun getListings(): Single<List<ChildrenData>> {
        return db.posts().getAll()
    }

    override fun getListings(subreddit: String): Single<List<ChildrenData>> {
        return db.posts().getBySubreddit(subreddit)
    }

    override fun isCached(): Single<Boolean> {
        return Single.fromCallable { false }
    }

    override fun setLastCacheTime(time: Long) {
        // not available
    }

    override fun isExpired(): Boolean {
        return false
    }
}