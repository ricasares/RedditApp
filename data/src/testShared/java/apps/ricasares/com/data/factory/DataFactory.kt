package apps.ricasares.com.data.factory

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by rush on 1/23/18.
 */
class DataFactory {
    companion object {
        fun randomString() : String {
            return java.util.UUID.randomUUID().toString()
        }

        fun randomInt() : Int {
            return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
        }

        fun randomBoolean(): Boolean {
            return Math.random() < 0.5
        }

        fun randomLong(): Long {
            return randomInt().toLong()
        }
    }
}