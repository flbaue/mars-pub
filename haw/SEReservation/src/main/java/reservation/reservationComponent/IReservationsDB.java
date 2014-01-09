package reservation.reservationComponent;

import reservation.reservationComponent.Reservation;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IReservationsDB {

    int getUniqueNumber();

    void saveReservation(Reservation reservation); //TODO throws exception!!

    Reservation getReservationByNumber(int number);

    List<Reservation> getReservationsByRoomNumber(int number);

    List<Reservation> getReservationsByGuestNumber(int number);
}
