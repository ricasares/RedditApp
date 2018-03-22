package apps.ricasares.com.domain.factory

import apps.ricasares.com.domain.factory.DataFactory.Companion.randomInt
import apps.ricasares.com.domain.factory.DataFactory.Companion.randomLong
import apps.ricasares.com.domain.factory.DataFactory.Companion.randomString
import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.Listing

/**
 * Created by ricardo casarez on 1/23/18.
 */
class ListingFactory {
    companion object {
        fun makeListing(count: Int) :Listing {
            return Listing(randomString(), randomString(), randomString(), randomString(), makeEntryList(count))
        }

        fun makeEntry() : Entry {
            return Entry(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomInt(),
                    randomInt(),
                    randomLong()
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