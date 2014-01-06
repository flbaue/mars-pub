package guestComponent;

import databaseService.IGuestsDB;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServicesForReservation {

    IGuestsDB guestDB;

    public GuestServicesForReservation(IGuestsDB guestDB) {
        this.guestDB = guestDB;
    }

    public void markGuestAsRegular(int number) {

        Guest guest = guestDB.searchGuestByNumber(number);
        guest.setRegularGuest(true);
    }
}
