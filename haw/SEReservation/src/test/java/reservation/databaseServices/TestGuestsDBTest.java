package reservation.databaseServices;

import org.junit.Test;
import reservation.guestComponent.EMailType;
import reservation.guestComponent.Guest;
import reservation.guestComponent.IGuestsDB;
import reservation.guestComponent.TestGuestsDB;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class TestGuestsDBTest {

    private IGuestsDB guestsDB = new TestGuestsDB();

    @Test
    public void testSearchGuestByNumber() throws Exception {
        Guest guest4 = guestsDB.getGuestByNumber(4);
        assertEquals("Stan Marsh", guest4.getName());
    }

    @Test
    public void testSearchGuestsByName() throws Exception {
        List<Guest> guests = guestsDB.getGuestsByName("Broflovski");
        assertEquals(2, guests.size());
    }

    @Test
    public void testSearchGuestByEMail() throws Exception {
        List<Guest> guests = guestsDB.getGuestByEMail("theCoon@southpark.de");
        assertEquals(1, guests.size());
        assertEquals("Eric Cartman", guests.get(0).getName());
    }

    @Test
    public void testSaveGuest() throws Exception {
        Guest guest = new Guest("Johnny Bravo", new EMailType("johnny@cartoon.com"));
        guestsDB.saveGuest(guest);
        List<Guest> test = guestsDB.getGuestsByName("Johnny Bravo");
        assertSame(guest, test.get(0));
    }
}
