package guestComponent;

import databaseService.IGuestsDB;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServices {

    IGuestsDB guestDB;

    public GuestServices(IGuestsDB guestDB) {
        this.guestDB = guestDB;
    }

    public Guest createGuest(int number, String name, String eMail) {
        Guest guest = new Guest(number, name, new EMailType(eMail));
        guestDB.saveGuest(guest);
        return guest;
    }

    public List<Guest> searchForGuest(String name) {
        return guestDB.searchGuestsByName(name);
    }
}
