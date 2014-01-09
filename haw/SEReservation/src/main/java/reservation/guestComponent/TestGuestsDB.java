package reservation.guestComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class TestGuestsDB implements IGuestsDB {

    private List<Guest> testGuests;

    public TestGuestsDB() {
        testGuests = new ArrayList<>();
        Guest guest = new Guest("Eric Cartman", new EMailType("theCoon@southpark.de"));
        guest.setNumber(1);
        testGuests.add(guest);

        guest = new Guest("Kenny McCormick", new EMailType("surviver@southpark.de"));
        guest.setNumber(2);
        testGuests.add(guest);


        guest = new Guest("Kyle Broflovski", new EMailType("redAleart@southpark.de"));
        guest.setNumber(3);
        testGuests.add(guest);


        guest = new Guest("Stan Marsh", new EMailType("StanTheMan@southpark.de"));
        guest.setNumber(4);
        testGuests.add(guest);


        guest = new Guest("Randy Marsh", new EMailType("ohYeah@southpark.de"));
        guest.setNumber(5);
        testGuests.add(guest);

        guest = new Guest("Gerald Broflovski", new EMailType("BlingBling@southpark.de"));
        guest.setNumber(5);
        testGuests.add(guest);
    }

    @Override
    public Guest getGuestByNumber(int number) {
        Guest result = null;
        for (Guest guest : testGuests) {
            if (guest.getNumber() == number) {
                result = guest;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Guest> getGuestsByName(String name) {
        List<Guest> results = new ArrayList<>();
        for (Guest guest : testGuests) {
            if (guest.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(guest);
            }
        }
        return results;
    }

    @Override
    public List<Guest> getGuestByEMail(String eMail) {
        List<Guest> results = new ArrayList<>();
        for (Guest guest : testGuests) {
            if (guest.getEmail().toString().toLowerCase().contains(eMail.toLowerCase())) {
                results.add(guest);
            }
        }
        return results;
    }

    @Override
    public void saveGuest(Guest guest) {

        testGuests.remove(guest);
        testGuests.add(guest);
    }

    @Override
    public int getUniqueNumber() {
        return testGuests.size() + 1;
    }
}
