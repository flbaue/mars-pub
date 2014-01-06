package reservation.guestComponent;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IGuestServices {

    Guest createGuest(String name, String eMail);

    List<Guest> searchForGuest(String name);

    Guest getGuestByNumber(int number);
}
