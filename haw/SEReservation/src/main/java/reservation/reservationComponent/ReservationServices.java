package reservation.reservationComponent;

import reservation.guestComponent.IGuestServicesForReservation;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class ReservationServices implements IReservationServices {

    private static final int RESERVATIONS_FOR_REGULAR = 5;
    private static final int ADDITIONAL_SERVICES_FOR_REGULAR = 3;
    private IReservationsDB reservationDB;
    private IAdditionalServicesDB additionalServicesDB;
    private IGuestServicesForReservation guestServicesForReservation;

    public ReservationServices(IReservationsDB reservationDB, IAdditionalServicesDB additionalServicesDB, IGuestServicesForReservation guestServicesForReservation) {
        this.reservationDB = reservationDB;
        this.additionalServicesDB = additionalServicesDB;
        this.guestServicesForReservation = guestServicesForReservation;
    }

    @Override
    public AdditionalService createAdditionalService(String service) {
        AdditionalService additionalService = new AdditionalService(service);
        additionalServicesDB.saveAdditionalService(additionalService);
        return additionalService;
    }

    @Override
    public Reservation createReservation(int guestNumber, int roomNumber) {

        int reservations = reservationDB.getReservationsByGuestNumber(guestNumber).size() + 1;
        if (reservations >= RESERVATIONS_FOR_REGULAR) {
            guestServicesForReservation.markGuestAsRegular(guestNumber);
        }

        Reservation reservation = new Reservation(guestNumber, roomNumber);
        reservationDB.saveReservation(reservation);
        return reservation;
    }

    @Override
    public void bookAdditionalService(int reservationNumber, int additionalServiceNumber) {

        Reservation reservation = reservationDB.getReservationByNumber(reservationNumber);
        reservation.addAdditionalService(additionalServiceNumber);
        reservationDB.saveReservation(reservation);


        int reservationWithExtra = 0;
        List<Reservation> reservations = reservationDB.getReservationsByGuestNumber(reservation.getGuestNumber());
        for (Reservation r : reservations) {
            if (r.getAdditionalServices().size() > 0) {
                reservationWithExtra += 1;
            }
        }
        if (reservationWithExtra >= ADDITIONAL_SERVICES_FOR_REGULAR) {
            guestServicesForReservation.markGuestAsRegular(reservation.getGuestNumber());
        }
    }
}
