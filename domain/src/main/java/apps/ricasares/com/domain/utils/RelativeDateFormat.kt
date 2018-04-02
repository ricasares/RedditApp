package apps.ricasares.com.domain.utils

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by rush on 3/30/18.
 */
class RelativeDateFormat {
    companion object {
        val times: MutableMap<String, Long> = LinkedHashMap()
        init {
            times["y"] = TimeUnit.DAYS.toMillis(365)
            times["mo"] = TimeUnit.DAYS.toMillis(30)
            times["w"] = TimeUnit.DAYS.toMillis(7)
            times["d"] = TimeUnit.DAYS.toMillis(1)
            times["h"] = TimeUnit.HOURS.toMillis(1)
            times["m"] = TimeUnit.MINUTES.toMillis(1)
        }
        private fun toRelative(duration: Long, level: Int) : String
        {
            val builder = StringBuilder()
            var temp = duration
            var l = 0
            for (time in times.entries) {
                var timeDelta = temp / time.value
                if (timeDelta > 0) {
                    builder.append(timeDelta)
                            .append(time.key)
                    temp -= time.value * timeDelta
                    if (++l >= level)
                        break
                }
            }
            if (builder.isEmpty()) {
                builder.append("now")
            }
            return builder.toString()
        }

        fun toRelateve(start: Long, end: Long) : String {
            return toRelative(end - start, 1)
        }

    }
}