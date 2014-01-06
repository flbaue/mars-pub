package reservation.reservationComponent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class AdditionalServiceTest {

    private AdditionalService additionalService;

    @Before
    public void setup() {
        additionalService = new AdditionalService(99, "WLAN");
    }

    @Test
    public void testGetNumber() throws Exception {
        assertEquals(99, additionalService.getNumber());
    }

    @Test
    public void testGetService() throws Exception {
        assertEquals("WLAN", additionalService.getService());
    }

    @Test
    public void testSetService() throws Exception {
        additionalService.setService("SPA");
        assertEquals("SPA", additionalService.getService());
    }
}
