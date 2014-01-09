package reservation.reservationComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class TestReservationsDB implements IReservationsDB {

    private List<Reservation> reservations;

    public TestReservationsDB() {
        reservations = new ArrayList<>();
//        reservations.add(new Reservation(getUniqueNumber(), 1, 101));
//        reservations.add(new Reservation(getUniqueNumber(), 2, 102));
//        reservations.add(new Reservation(getUniqueNumber(), 3, 103));
//        reservations.add(new Reservation(getUniqueNumber(), 4, 104));
//        reservations.add(new Reservation(getUniqueNumber(), 5, 105));
//        reservations.add(new Reservation(getUniqueNumber(), 1, 110));
//        reservations.add(new Reservation(getUniqueNumber(), 1, 110));
//        reservations.add(new Reservation(getUniqueNumber(), 1, 110));
//        reservations.add(new Reservation(getUniqueNumber(), 1, 110));
    }

    @Override
    public int getUniqueNumber() {
        return reservations.size() + 1;
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservations.remove(reservation);
        reservations.add(reservation);
    }

    @Override
    public Reservation getReservationByNumber(int number) {
        Reservation result = null;
        for (Reservation reservation : reservations) {
            if (reservation.getNumber() == number) {
                result = reservation;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Reservation> getReservationsByRoomNumber(int number) {
        List<Reservation> results = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getRoomNumber() == number) {
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<Reservation> getReservationsByGuestNumber(int number) {
        List<Reservation> results = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getGuestNumber() == number) {
                results.add(reservation);
            }
        }
        return results;
    }
}
