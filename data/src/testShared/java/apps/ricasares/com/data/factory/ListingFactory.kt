package apps.ricasares.com.data.factory

import apps.ricasares.com.data.factory.DataFactory.Companion.randomBoolean
import apps.ricasares.com.data.factory.DataFactory.Companion.randomInt
import apps.ricasares.com.data.factory.DataFactory.Companion.randomLong
import apps.ricasares.com.data.factory.DataFactory.Companion.randomString
import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.ImagePreview
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.model.PostHint

/**
 * Created by rush on 1/23/18.
 */
class ListingFactory {
    companion object {
        fun makeListing(count: Int) :Listing {
            return Listing(makeEntryList(count))
        }

        fun makeEntry() : Entry {
            return Entry(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomInt(),
                    randomInt(),
                    randomLong(),
                    randomBoolean(),
                    PostHint.LINK,
                    ImagePreview(randomString(), randomInt(), randomInt())
            )
        }

        fun makeEntryList(count: Int) : List<Entry> {
            var list = mutableListOf<Entry>()
            for (i in 0 until count) {
                list.add(makeEntry())
            }
            return list
        }
    }
}