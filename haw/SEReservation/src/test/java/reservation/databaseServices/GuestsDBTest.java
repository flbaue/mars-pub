package reservation.databaseServices;

import org.junit.BeforeClass;
import org.junit.Test;
import reservation.guestComponent.EMailType;
import reservation.guestComponent.Guest;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Florian Bauer on 05.01.14.
 * flbaue@posteo.de
 */
public class GuestsDBTest {

    private static IDBServicesFactory servicesFactory;
    private static IGuestsDB guestsDB;

    @BeforeClass
    public static void setUp() throws Exception {
        servicesFactory = new DBServicesFactory(DBServicesFactory.DATABASE_ENVIRONMENT, "org.sqlite.JDBC", "jdbc:sqlite:GuestsDBTest.db");
        guestsDB = servicesFactory.getGuestsDB();
        Guest guest = new Guest(guestsDB.getUniqueNumber(), "Eric Cartman", new EMailType("thecoon@southpark.de"));
        guestsDB.saveGuest(guest);
    }

    @Test
    public void testGetUniqueNumber() throws Exception {
        int id = guestsDB.getUniqueNumber();
        Guest guest = guestsDB.getGuestByNumber(id);
        assertNull(guest);
    }

    @Test
    public void testGetGuestByNumber() throws Exception {
        Guest guest = guestsDB.getGuestByNumber(1);
        assertNotNull(guest);
    }

    @Test
    public void testGetGuestsByName() throws Exception {
        List<Guest> guests = guestsDB.getGuestsByName("Eric");
        assertNotNull(guests);
        assertEquals(1, guests.size());
        assertEquals("Eric Cartman", guests.get(0).getName());
    }

    @Test
    public void testGetGuestByEMail() throws Exception {
        List<Guest> guests = guestsDB.getGuestByEMail("thecoon@southpark");
        assertNotNull(guests);
        assertEquals(1, guests.size());
        assertEquals("Eric Cartman", guests.get(0).getName());
    }

    @Test
    public void testSaveGuest() throws Exception {
        Guest guest = new Guest(guestsDB.getUniqueNumber(), "Bob Bobbsen", new EMailType("a@b.c"));
        guestsDB.saveGuest(guest);
    }
}
