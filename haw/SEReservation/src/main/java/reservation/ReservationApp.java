package reservation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import reservation.guestComponent.Guest;
import reservation.guestComponent.IGuestServices;
import reservation.reservationComponent.AdditionalService;
import reservation.reservationComponent.IReservationServices;
import reservation.reservationComponent.Reservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Florian Bauer on 04.01.14. flbaue@posteo.de
 */
public class ReservationApp {

    public static void main(String[] args) {
        ReservationApp reservationApp = new ReservationApp();
        reservationApp.run();
    }

    public void run() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        IGuestServices guestServices = applicationContext.getBean("GuestServices", IGuestServices.class);
        IReservationServices reservationServices = applicationContext.getBean("ReservationServices", IReservationServices.class);
        MessageSource messageSource = applicationContext.getBean("MessageSource", MessageSource.class);
        Resource resource = applicationContext.getResource("testuser.txt");
        String[] guestR = new String[2];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                guestR = line.split(";");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Guest guest = guestServices.createGuest(guestR[0], guestR[1]);
        AdditionalService service = reservationServices.createAdditionalService("Abendbrot");

        System.out.println("Gast Nr: " + guest.getNumber() + " ist Stammgast: " + guest.isRegularGuest());

        Reservation reservation = reservationServices.createReservation(guest.getNumber(), 101);
        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());

        reservation = reservationServices.createReservation(guest.getNumber(), 102);
        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());

        reservation = reservationServices.createReservation(guest.getNumber(), 101);
        reservationServices.bookAdditionalService(reservation.getNumber(), service.getNumber());

        System.out.println("Gast Nr: " + guest.getNumber() + " ist Stammgast: " + guestServices.getGuestByNumber(guest.getNumber()).isRegularGuest());

        System.out.println(messageSource.getMessage("welcome", new Object[]{"Spring"}, Locale.GERMAN));
        System.out.println(messageSource.getMessage("welcome", new Object[]{"Spring"}, Locale.ENGLISH));
    }
}
