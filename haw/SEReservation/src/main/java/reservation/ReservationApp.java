package reservation;

import reservation.databaseServices.DBServicesFactory;
import reservation.databaseServices.IDBServicesFactory;
import reservation.guestComponent.*;
import reservation.reservationComponent.AdditionalService;
import reservation.reservationComponent.IReservationServices;
import reservation.reservationComponent.Reservation;
import reservation.reservationComponent.ReservationServices;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class ReservationApp {

    public static void main(String[] args) {

        IDBServicesFactory idbServicesFactory = new DBServicesFactory(DBServicesFactory.DATABASE_ENVIRONMENT,"org.sqlite.JDBC","jdbc:sqlite:ReservationSystem.db");

        IGuestServices guestServices = new GuestServices(idbServicesFactory);
        IReservationServices reservationServices = new ReservationServices(idbServicesFactory);

        Guest guest = guestServices.createGuest("Max Mustermann", "hausbot@elbe.de");
        AdditionalService service = reservationServices.createAdditionalService("Abendbrot");

        System.out.println("Gast Nr: " + guest.getNumber() + " ist Stammgast: " + guest.isRegularGuest());

        Reservation reservation = reservationServices.createReservation(guest.getNumber(), 101);
        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());

        reservation = reservationServices.createReservation(guest.getNumber(), 102);
        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());

        reservation = reservationServices.createReservation(guest.getNumber(), 101);
        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());

        System.out.println("Gast Nr: " + guest.getNumber() + " ist Stammgast: " + guestServices.getGuestByNumber(guest.getNumber()).isRegularGuest());
    }
}
