package reservation.guestComponent;

import reservation.databaseServices.IDBServicesFactory;
import reservation.databaseServices.IGuestsDB;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class GuestServices implements IGuestServices {

    private IGuestsDB guestDB;

    public GuestServices(IDBServicesFactory servicesFactory) {
        this.guestDB = servicesFactory.getGuestsDB();
    }

    public Guest createGuest(String name, String eMail) {
        Guest guest = new Guest(guestDB.getUniqueNumber(), name, new EMailType(eMail));
        guestDB.saveGuest(guest);
        return guest;
    }

    public List<Guest> searchForGuest(String name) {
        return guestDB.getGuestsByName(name);
    }
}
