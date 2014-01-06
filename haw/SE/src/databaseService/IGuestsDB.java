package databaseService;

import guestComponent.Guest;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IGuestsDB {
    Guest searchGuestByNumber(int number);
    List<Guest> searchGuestsByName(String name);
    List<Guest> searchGuestByEMail(String eMail);
    void saveGuest(Guest guest); //TODO throws exception!!
}
