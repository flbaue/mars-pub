package reservation.reservationComponent;

import reservation.databaseServices.IAdditionalServicesDB;
import reservation.databaseServices.IDBServicesFactory;
import reservation.databaseServices.IReservationsDB;
import reservation.guestComponent.GuestServicesForReservation;
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

    public ReservationServices(IDBServicesFactory servicesFactory) {
        this.reservationDB = servicesFactory.getReservationsDB();
        this.additionalServicesDB = servicesFactory.getAdditionalServicesDB();
        this.guestServicesForReservation = new GuestServicesForReservation(servicesFactory);
    }

    @Override
    public AdditionalService createAdditionalService(String service) {
        AdditionalService additionalService = new AdditionalService(additionalServicesDB.getUniqueNumber(), service);
        additionalServicesDB.saveAdditionalService(additionalService);
        return additionalService;
    }

    @Override
    public Reservation createReservation(int guestNumber, int roomNumber) {

        int reservations = reservationDB.getReservationsByGuestNumber(guestNumber).size() + 1;
        if (reservations >= RESERVATIONS_FOR_REGULAR) {
            guestServicesForReservation.markGuestAsRegular(guestNumber);
        }

        Reservation reservation = new Reservation(reservationDB.getUniqueNumber(), guestNumber, roomNumber);
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
