package reservation.guestComponent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestTest {

    Guest guest;

    @Before
    public void setup() {
        guest = new Guest(99, "Bob Bobbsen", new EMailType("test@test.de"));
    }

    @Test
    public void testGetNumber() throws Exception {
        assertEquals(99, guest.getNumber());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Bob Bobbsen", guest.getName());
    }

    @Test
    public void testSetName() throws Exception {
        guest.setName("Timmy");
        assertEquals("Timmy", guest.getName());
    }

    @Test
    public void testGetEmail() throws Exception {
        assertEquals("test@test.de", guest.getEmail().toString());
    }

    @Test
    public void testSetEmail() throws Exception {
        guest.setEmail(new EMailType("asd@asd.asd"));
        assertEquals("asd@asd.asd", guest.getEmail().toString());
    }

    @Test
    public void testIsRegularGuest() throws Exception {
        assertFalse(guest.isRegularGuest());
    }

    @Test
    public void testSetRegularGuest() throws Exception {
        guest.setRegularGuest(true);
        assertTrue(guest.isRegularGuest());
    }
}
