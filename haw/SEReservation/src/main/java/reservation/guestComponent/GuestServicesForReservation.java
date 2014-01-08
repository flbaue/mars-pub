package reservation.guestComponent;

import reservation.databaseServices.IDBServicesFactory;
import reservation.databaseServices.IGuestsDB;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServicesForReservation implements IGuestServicesForReservation {

    private IGuestsDB guestDB;

    public GuestServicesForReservation(IDBServicesFactory servicesFactory) {
        this.guestDB = servicesFactory.getGuestsDB();
    }

    public void markGuestAsRegular(int number) {

        Guest guest = guestDB.getGuestByNumber(number);

        guest.setRegularGuest(true);
        guestDB.saveGuest(guest);
    }
}
