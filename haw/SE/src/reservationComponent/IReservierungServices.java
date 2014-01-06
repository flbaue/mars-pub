package reservationComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IReservierungServices {

    AdditionalService createAdditionalService(int description);

    Reservation createReservation(int guestNumber, int roomNumber);

    void bookAdditionalService(int reservationNumber, int additionalServiceNumber);
}
