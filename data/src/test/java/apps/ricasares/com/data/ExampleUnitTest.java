package apps.ricasares.com.data;

import org.junit.Test;

import apps.ricasares.com.data.di.DaggerDataComponent;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        assertNotNull(DaggerDataComponent.create().redditApi());
    }
}