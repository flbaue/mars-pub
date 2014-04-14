package reservation.guestComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServicesForReservation implements IGuestServicesForReservation {

    private IGuestsDB guestDB;

    public GuestServicesForReservation(IGuestsDB guestDB) {
        this.guestDB = guestDB;
    }

    public void markGuestAsRegular(int number) {

        Guest guest = guestDB.getGuestByNumber(number);

        guest.setRegularGuest(true);
        guestDB.saveGuest(guest);
    }
}
