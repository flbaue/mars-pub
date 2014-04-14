package reservation.guestComponent;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServices implements IGuestServices {

    private IGuestsDB guestsDB;

    public GuestServices(IGuestsDB guestsDB) {
        this.guestsDB = guestsDB;
    }

    public Guest createGuest(String name, String eMail) {
        Guest guest = new Guest(name, new EMailType(eMail));
        guestsDB.saveGuest(guest);
        return guest;
    }

    public List<Guest> searchForGuest(String name) {
        return guestsDB.getGuestsByName(name);
    }

    @Override
    public Guest getGuestByNumber(int number) {
        return guestsDB.getGuestByNumber(number);
    }
}
