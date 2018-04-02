package apps.ricasares.com.domain.factory

import apps.ricasares.com.domain.factory.DataFactory.Companion.randomBoolean
import apps.ricasares.com.domain.factory.DataFactory.Companion.randomInt
import apps.ricasares.com.domain.factory.DataFactory.Companion.randomLong
import apps.ricasares.com.domain.factory.DataFactory.Companion.randomString
import apps.ricasares.com.domain.model.Entry
import apps.ricasares.com.domain.model.Listing
import apps.ricasares.com.domain.model.PostHint

/**
 * Created by ricardo casarez on 1/23/18.
 */
class ListingFactory {
    companion object {
        fun makeListing(count: Int) :Listing {
            return Listing(randomString(), randomString(), randomString(), randomString(), makeEntryList(count))
        }

        fun makeListingOf(count: Int, postHint: PostHint) : Listing {
            return Listing(randomString(), randomString(), randomString(), randomString(), makeEntryListOf(count, postHint))
        }

        fun makeEntry() : Entry {
            return Entry(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomInt(),
                    randomInt(),
                    randomLong(),
                    randomBoolean(),
                    PostHint.LINK
            )
        }

        fun makeEntry(hint: PostHint) : Entry {
            return Entry(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomInt(),
                    randomInt(),
                    randomLong(),
                    randomBoolean(),
                    hint
            )
        }

        fun makeEntryList(count: Int) : List<Entry> {
            var list = mutableListOf<Entry>()
            for (i in 0 until count) {
                list.add(makeEntry())
            }
            return list
        }

        fun makeEntryListOf(count: Int, hint: PostHint) : List<Entry> {
            var list = mutableListOf<Entry>()
            for (i in 0 until count) {
                list.add(makeEntry(hint))
            }
            return list
        }
    }
}