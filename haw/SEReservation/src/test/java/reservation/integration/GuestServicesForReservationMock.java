package reservation.integration;

import reservation.databaseServices.IDBServicesFactory;
import reservation.guestComponent.IGuestsDB;
import reservation.guestComponent.Guest;
import reservation.guestComponent.IGuestServicesForReservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Florian Bauer on 09.01.14.
 * flbaue@posteo.de
 */
public class GuestServicesForReservationMock implements IGuestServicesForReservation {

    private IGuestsDB guestDB;

    public GuestServicesForReservationMock(IDBServicesFactory servicesFactory) {
        this.guestDB = servicesFactory.getGuestsDB();
    }

    @Override
    public void markGuestAsRegular(int number) {
        Guest guest = guestDB.getGuestByNumber(number);

        guest.setRegularGuest(true);
        guestDB.saveGuest(guest);
    }
}
