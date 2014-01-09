package reservation.reservationComponent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class ReservationTest {

    private Reservation reservation;

    @Before
    public void setup() {
        reservation = new Reservation(1, 13);
    }

    @Test
    public void testGetNumber() throws Exception {
//        Assert.assertEquals(99, reservation.getNumber());
    }

    @Test
    public void testGetRoom() throws Exception {
//        Assert.assertEquals(13, reservation.getRoomNumber());
    }

    @Test
    public void testSetRoom() throws Exception {
        reservation.setRoomNumber(25);
        Assert.assertEquals(25, reservation.getRoomNumber());
    }
}
