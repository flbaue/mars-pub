package reservation.guestComponent;

import org.junit.Before;
import org.junit.Test;
import reservation.databaseServices.DBServicesFactory;
import reservation.databaseServices.IDBServicesFactory;
import reservation.databaseServices.IGuestsDB;
import reservation.databaseServices.TestGuestsDB;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServicesForReservationTest {

    private IDBServicesFactory servicesFactory;

    @Before
    public void setup(){
        servicesFactory = new DBServicesFactory(DBServicesFactory.TEST_ENVIRONMENT, null, null);
    }

    @Test
    public void testMarkGuestAsRegular() throws Exception {

        int customerNumber = 1;

        IGuestsDB guestsDB = servicesFactory.getGuestsDB();
        IGuestServicesForReservation guestServicesForReservation = new GuestServicesForReservation(servicesFactory);
        Guest guest = guestsDB.getGuestByNumber(customerNumber);

        assertFalse(guest.isRegularGuest());

        guestServicesForReservation.markGuestAsRegular(customerNumber);
        guest = guestsDB.getGuestByNumber(customerNumber);

        assertTrue(guest.isRegularGuest());
    }
}
