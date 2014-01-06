package reservation.databaseServices;

import reservation.guestComponent.Guest;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IGuestsDB {

    int getUniqueNumber();

    Guest getGuestByNumber(int number);

    List<Guest> getGuestsByName(String name);

    List<Guest> getGuestByEMail(String eMail);

    void saveGuest(Guest guest); //TODO throws exception!!
}
