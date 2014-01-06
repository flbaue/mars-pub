package reservation.reservationComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IReservationServices {

    AdditionalService createAdditionalService(String service);

    Reservation createReservation(int guestNumber, int roomNumber);

    void bookAdditionalService(int reservationNumber, int additionalServiceNumber);
}
