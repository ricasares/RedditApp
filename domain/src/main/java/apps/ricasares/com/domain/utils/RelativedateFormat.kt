package apps.ricasares.com.domain.utils

import java.util.LinkedHashMap
import java.util.concurrent.TimeUnit

/**
 * Created by rush on 3/30/18.
 */
class RelativedateFormat {
    companion object {
        val times: MutableMap<String, Long> = LinkedHashMap()
        init {
            times["y"] = TimeUnit.DAYS.toMillis(365)
            times["mo"] = TimeUnit.DAYS.toMillis(30)
            times["w"] = TimeUnit.DAYS.toMillis(7)
            times["d"] = TimeUnit.DAYS.toMillis(1)
            times["h"] = TimeUnit.HOURS.toMillis(1)
            times["m"] = TimeUnit.MINUTES.toMillis(1)
            //times["s"] = TimeUnit.SECONDS.toMillis(1)
        }
        fun toRelative(duration: Long) : String{
            val builder = StringBuilder()
            var temp = duration
            for (time in times.entries) {
                var timeDelta = temp / time.value
                if (timeDelta > 0) {
                    builder.append(timeDelta)
                            .append(time.key)
                    temp -= time.value * timeDelta
                }
            }
            if (builder.isEmpty()) {
                builder.append("now")
            }
            return builder.toString()
        }
    }
}