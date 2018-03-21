package apps.ricasares.com.data.factory

import apps.ricasares.com.data.entity.*
import apps.ricasares.com.data.factory.DataFactory.Companion.randomBoolean
import apps.ricasares.com.data.factory.DataFactory.Companion.randomInt
import apps.ricasares.com.data.factory.DataFactory.Companion.randomLong
import apps.ricasares.com.data.factory.DataFactory.Companion.randomString

/**
 * Created by rush on 1/23/18.
 */
class RedditResponseFactory {
    companion object {
        fun makeRedditResponse(count: Int) : RedditResponse {
            return RedditResponse(randomString(), makeDataResponse(count))
        }

        fun makeRedditResponse(count: Int, type: String) : RedditResponse {
            return RedditResponse(type, makeDataResponse(count))
        }

        fun makeDataResponse(count: Int) : ObjectData {
            return ObjectData(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    makeChildrenResponseList(count)
            )
        }

        fun makeChildrenResponseList(count: Int) : List<Children> {
            var list = mutableListOf<Children>()
            for (i in 0 until count) {
                list.add(makeChildrenResponse())
            }
            return list
        }

        fun makeChildrenResponse() : Children {
            return Children(randomString(), makeLink())
        }

        fun makeMedia() : Media {
            return Media(
                    makeRedditVideo(),
                    randomInt(),
                    randomBoolean(),
                    randomBoolean(),
                    randomString(),
                    randomBoolean(),
                    randomString()
            )
        }

        fun makeRedditVideo() : RedditVideo {
            return RedditVideo(
                    randomString(),
                    randomInt(),
                    randomInt(),
                    randomString(),
                    randomString(),
                    randomInt(),
                    randomString(),
                    randomBoolean(),
                    randomString()
            )
        }

        fun makeLink() : ChildrenData {
            return ChildrenData(
                    randomString(),
                    randomString(),
                    randomString(),
                    randomInt(),
                    randomInt(),
                    randomBoolean(),
                    randomLong(),
                    randomLong(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomBoolean(),
                    randomString(),
                    randomBoolean(),
                    randomBoolean(),
                    randomString(),
                    randomString(),
                    randomBoolean(),
                    makeMedia(),
                    makeMedia(),
                    randomInt(),
                    randomBoolean(),
                    randomString(),
                    randomBoolean(),
                    randomInt(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    randomString(),
                    //randomLong(),
                    randomString(),
                    randomBoolean()
            )
        }
    }
}