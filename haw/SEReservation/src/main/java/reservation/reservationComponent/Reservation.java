package reservation.reservationComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class Reservation {

    private int number;
    private int guestNumber;
    private int roomNumber;
    private List<Integer> additionalServices;

    public Reservation(int guestNumber, int roomNumber) {
        this.number = -1;
        this.guestNumber = guestNumber;
        this.roomNumber = roomNumber;
        this.additionalServices = new ArrayList<>();
    }

    public void setNumber(int number) {
        if(this.number == -1) {
            this.number = number;
        }
    }

    public int getNumber() {
        return number;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void addAdditionalService(int additionalServiceNumber) {
        additionalServices.add(additionalServiceNumber);
    }

    public List<Integer> getAdditionalServices() {
        return new ArrayList<>(additionalServices);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (guestNumber != that.guestNumber) return false;
        if (number != that.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + guestNumber;
        return result;
    }
}
