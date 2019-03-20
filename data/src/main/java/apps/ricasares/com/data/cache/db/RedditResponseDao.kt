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
    @Query("SELECT * FROM posts ORDER BY indexInResponse ASC")
    fun getAll() : Single<List<ChildrenData>>

    @Query("SELECT * FROM posts WHERE subreddit = :subreddit ORDER BY indexInResponse ASC")
    fun getBySubreddit(subreddit : String) : Single<List<ChildrenData>>

    @Insert
    fun insert(posts: List<ChildrenData>)

    @Query("SELECT MAX(indexInResponse) + 1 FROM posts WHERE subreddit = :subreddit")
    fun getNextIndexInSubreddit(subreddit: String) : Int

    @Query("DELETE FROM posts WHERE subreddit = :subreddit")
    fun deleteBySubreddit(subreddit: String)

    @Query("DELETE FROM posts")
    fun deleteAll()
}