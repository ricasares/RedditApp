package apps.ricasares.com.data.cache.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import apps.ricasares.com.data.entity.ChildrenData
import io.reactivex.Single

/**
 * Created by ricardo casarez on 1/24/18.
 */
@Dao
interface RedditResponseDao {
    @Query("SELECT * FROM posts WHERE subreddit = :subreddit ORDER BY indexInResponse ASC")
    fun getBySubbreddit(subreddit : String) : Single<ChildrenData>

    @Insert
    fun insert(posts: ChildrenData) : Long

    @Query("SELECT MAX(indexInResponse) + 1 FROM posts WHERE subreddit = :subreddit")
    fun getNextIndexInSubreddit(subreddit: String) : Int

    @Query("DELETE FROM posts WHERE subreddit = :subreddit")
    fun deleteBySubreddit(subreddit: String)
}