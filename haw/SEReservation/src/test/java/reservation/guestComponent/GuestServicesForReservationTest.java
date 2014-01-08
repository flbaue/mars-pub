package reservation.guestComponent;

import org.junit.Before;
import org.junit.Test;
import reservation.databaseServices.DBServicesFactory;
import reservation.databaseServices.IDBServicesFactory;
import reservation.reservationComponent.AdditionalService;
import reservation.reservationComponent.IReservationServices;
import reservation.reservationComponent.ReservationServices;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServicesForReservationTest {

    private IDBServicesFactory servicesFactory;

    @Before
    public void setup() {
        //    servicesFactory = new DBServicesFactory(DBServicesFactory.TEST_ENVIRONMENT, null, null);
        servicesFactory = new DBServicesFactory(DBServicesFactory.DATABASE_ENVIRONMENT, "org.sqlite.JDBC", "jdbc:sqlite:GuestsDBTest.db");

    }

    @Test
    public void testMarkGuestAsRegular() throws Exception {


        int customerNumber;

        IGuestServices guestServices = new GuestServices(servicesFactory);
        IReservationServices reservationServices = new ReservationServices(servicesFactory);

        Guest guest = guestServices.createGuest("Max Mustermann", "hausbot@elbe.de");
        customerNumber = guest.getNumber();
        AdditionalService service = reservationServices.createAdditionalService("Abendbrot");

        assertFalse(guest.isRegularGuest());


        IGuestServicesForReservation guestServicesForReservation = new GuestServicesForReservation(servicesFactory);
        guestServicesForReservation.markGuestAsRegular(customerNumber);
        guest = guestServices.getGuestByNumber(customerNumber);
        assertTrue(guest.isRegularGuest());
    }
}
