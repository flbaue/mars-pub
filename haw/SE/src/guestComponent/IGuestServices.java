package guestComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IGuestServices {

    Guest createGuest(int number, String name, String eMail);

    Guest searchForGuest(String name);
}
