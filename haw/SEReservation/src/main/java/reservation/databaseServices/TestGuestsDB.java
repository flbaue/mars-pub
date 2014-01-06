package reservation.databaseServices;

import reservation.guestComponent.EMailType;
import reservation.guestComponent.Guest;

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
        testGuests.add(new Guest(1, "Eric Cartman", new EMailType("theCoon@southpark.de")));
        testGuests.add(new Guest(2, "Kenny McCormick", new EMailType("surviver@southpark.de")));
        testGuests.add(new Guest(3, "Kyle Broflovski", new EMailType("redAleart@southpark.de")));
        testGuests.add(new Guest(4, "Stan Marsh", new EMailType("StanTheMan@southpark.de")));
        testGuests.add(new Guest(5, "Randy Marsh", new EMailType("ohYeah@southpark.de")));
        testGuests.add(new Guest(6, "Gerald Broflovski", new EMailType("BlingBling@southpark.de")));
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
