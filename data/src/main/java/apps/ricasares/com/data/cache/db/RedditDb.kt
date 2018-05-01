package apps.ricasares.com.data.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import apps.ricasares.com.data.entity.ChildrenData

@Database(
        entities = arrayOf(ChildrenData::class),
        version = 1,
        exportSchema = false
)
abstract class RedditDb : RoomDatabase() {
    abstract fun posts(): RedditResponseDao
}