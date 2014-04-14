package reservation.guestComponent;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServicesTest {

//    private IDBServicesFactory servicesFactory;

    @Before
    public void setup() {
        // servicesFactory = new DBServicesFactory(DBServicesFactory.TEST_ENVIRONMENT,null,null);
//        servicesFactory = new DBServicesFactory(DBServicesFactory.DATABASE_ENVIRONMENT, "org.sqlite.JDBC", "jdbc:sqlite:GuestsDBTest.db");

    }

    @Test
    public void testCreateGuest() throws Exception {
//        IGuestsDB guestsDB = new TestGuestsDB();
//        IGuestServices guestServices = new GuestServices(servicesFactory);
//
//        Guest guest = guestServices.createGuest("Bob Bobby", "bobby@provider.de");
//
//        assertEquals("Bob Bobby", guest.getName());
//        assertEquals("bobby@provider.de", guest.getEmail().toString());
    }

    @Test
    public void testSearchForGuest() throws Exception {
//        IGuestsDB guestsDB = servicesFactory.getGuestsDB();
//        IGuestServices guestServices = new GuestServices(servicesFactory);
//        guestsDB.saveGuest(new Guest("Kyle Broflovski",new EMailType("abc@abc.de")));
//
//
//        List<Guest> guests = guestServices.searchForGuest("Kyle Broflovski");
//
//        assertEquals("Kyle Broflovski", guests.get(0).getName());
    }
}
