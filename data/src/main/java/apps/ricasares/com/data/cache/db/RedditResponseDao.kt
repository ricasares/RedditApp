package apps.ricasares.com.data.cache.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import apps.ricasares.com.data.entity.RedditResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by ricardo casarez on 1/24/18.
 */
@Dao
interface RedditResponseDao {
    @Query("SELECT * FROM redditResponse")
    fun getAll() : Single<RedditResponse>

    @Insert
    fun insert(response: RedditResponse) : Completable

    @Delete
    fun deleteAll()
}