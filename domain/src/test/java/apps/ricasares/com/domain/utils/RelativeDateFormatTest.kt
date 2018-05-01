package apps.ricasares.com.domain.utils

import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.concurrent.TimeUnit

class RelativeDateFormatTest {
    var now = System.currentTimeMillis()

    @Test
    fun testSeconds() {
        assertEquals("now", RelativeDateFormat.toRelateve(now, now + TimeUnit.SECONDS.toMillis(1)))
        assertEquals("now", RelativeDateFormat.toRelateve(now, now + TimeUnit.SECONDS.toMillis(59)))
        assertNotEquals("now", RelativeDateFormat.toRelateve(now, now + TimeUnit.SECONDS.toMillis(60)))
    }

    @Test
    fun testMinutes() {
        assertEquals("1m", RelativeDateFormat.toRelateve(now, now + TimeUnit.MINUTES.toMillis(1)))
        assertEquals("59m", RelativeDateFormat.toRelateve(now, now + TimeUnit.MINUTES.toMillis(59)))
        assertNotEquals("62m", RelativeDateFormat.toRelateve(now, now + TimeUnit.MINUTES.toMillis(60)))
    }

    @Test
    fun testHours() {
        assertEquals("1h", RelativeDateFormat.toRelateve(now, now + TimeUnit.HOURS.toMillis(1)))
        assertEquals("23h", RelativeDateFormat.toRelateve(now, now + TimeUnit.HOURS.toMillis(23)))
        assertNotEquals("24h", RelativeDateFormat.toRelateve(now, now + TimeUnit.HOURS.toMillis(24)))
    }

    @Test
    fun testDays() {
        assertEquals("1d", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(1)))
        assertEquals("6d", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(6)))
        assertNotEquals("7d", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(7)))
    }

    @Test
    fun testWeek() {
        assertEquals("1w", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(1 * 7)))
        assertEquals("4w", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(4 * 7)))
        assertNotEquals("5w", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(5 * 7)))
    }

    @Test
    fun testMonth() {
        assertEquals("1mo", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(5 * 7)))
        assertEquals("11mo", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(50 * 7)))
        assertNotEquals("12mo", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(54 * 7)))
    }

    @Test
    fun testYear() {
        assertEquals("12mo", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(360)))
        assertEquals("1y", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(365)))
        assertEquals("2y", RelativeDateFormat.toRelateve(now, now + TimeUnit.DAYS.toMillis(740)))
    }
}