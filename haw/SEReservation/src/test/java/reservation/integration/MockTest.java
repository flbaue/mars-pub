package reservation.integration;

import org.junit.Test;
import reservation.databaseServices.*;
import reservation.guestComponent.*;
import reservation.reservationComponent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Florian Bauer on 09.01.14.
 * flbaue@posteo.de
 */
public class MockTest {

    @Test
    public void guestIsRegularThroughAdditionalsTest() throws Exception {
        IDBServicesFactory idbServicesFactory = new DBServicesFactory(DBServicesFactory.DATABASE_ENVIRONMENT, "org.sqlite.JDBC", "jdbc:sqlite:ReservationSystem.db");

        IGuestsDB guestsDB = idbServicesFactory.getGuestsDB();
        IReservationsDB reservationsDB = idbServicesFactory.getReservationsDB();
        IAdditionalServicesDB additionalServicesDB = idbServicesFactory.getAdditionalServicesDB();

        IGuestServices guestServices = new GuestServices(idbServicesFactory);
        IReservationServices reservationServices = new ReservationServices(idbServicesFactory);

        Guest guest = guestServices.createGuest("Max Mustermann", "hausbot@elbe.de");
        assertEquals(guest, guestsDB.getGuestByNumber(guest.getNumber()));

        AdditionalService service = reservationServices.createAdditionalService("Abendbrot");
        assertEquals(service, additionalServicesDB.getAdditionalServiceByNumber(service.getNumber()));

        assertFalse(guestsDB.getGuestByNumber(guest.getNumber()).isRegularGuest());

        Reservation reservation = reservationServices.createReservation(guest.getNumber(), 101);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());
        assertEquals(1,reservationsDB.getReservationByNumber(reservation.getNumber()).getAdditionalServices().size());

        reservation = reservationServices.createReservation(guest.getNumber(), 102);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());
        assertEquals(1,reservationsDB.getReservationByNumber(reservation.getNumber()).getAdditionalServices().size());

        reservation = reservationServices.createReservation(guest.getNumber(), 101);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());
        assertEquals(1,reservationsDB.getReservationByNumber(reservation.getNumber()).getAdditionalServices().size());

        assertTrue(guestsDB.getGuestByNumber(guest.getNumber()).isRegularGuest());
    }

    @Test
    public void guestIsRegularThroughReservationsTest() throws Exception {
        IDBServicesFactory idbServicesFactory = new DBServicesFactory(DBServicesFactory.DATABASE_ENVIRONMENT, "org.sqlite.JDBC", "jdbc:sqlite:ReservationSystem.db");

        IGuestsDB guestsDB = idbServicesFactory.getGuestsDB();
        IReservationsDB reservationsDB = idbServicesFactory.getReservationsDB();
        IAdditionalServicesDB additionalServicesDB = idbServicesFactory.getAdditionalServicesDB();

        IGuestServices guestServices = new GuestServices(idbServicesFactory);
        IReservationServices reservationServices = new ReservationServices(idbServicesFactory);

        Guest guest = guestServices.createGuest("Max Beta", "hausbot@elbe.de");
        assertEquals(guest, guestsDB.getGuestByNumber(guest.getNumber()));

        assertFalse(guestsDB.getGuestByNumber(guest.getNumber()).isRegularGuest());

        Reservation reservation = reservationServices.createReservation(guest.getNumber(), 101);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        reservation = reservationServices.createReservation(guest.getNumber(), 102);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        reservation = reservationServices.createReservation(guest.getNumber(), 101);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        reservation = reservationServices.createReservation(guest.getNumber(), 101);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        assertFalse(guestsDB.getGuestByNumber(guest.getNumber()).isRegularGuest());

        reservation = reservationServices.createReservation(guest.getNumber(), 101);
        assertEquals(reservation,reservationsDB.getReservationByNumber(reservation.getNumber()));

        assertTrue(guestsDB.getGuestByNumber(guest.getNumber()).isRegularGuest());
    }

}
