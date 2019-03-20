package apps.ricasares.com.data.cache

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import apps.ricasares.com.data.cache.db.RedditDb
import apps.ricasares.com.data.factory.RedditResponseFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListingDbCacheTestA {
    private lateinit var db: RedditDb
    private lateinit var listingCache: ListingCache

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), RedditDb::class.java)
                .build()
        listingCache = ListingDbCache(db)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testSaveListing() {
        val childrenData = RedditResponseFactory.makeChildreDataList(5)
        var insertObserver = listingCache.saveListings(childrenData).test()
        insertObserver.assertComplete()

        val getObserver = db.posts().getAll().test()
        getObserver.assertComplete()
        getObserver.assertValue {
            it -> it == childrenData
        }
    }

    @Test
    fun testGetFromSubreddit() {

    }
}