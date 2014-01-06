package reservationComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class Reservation {

    private final int number;
    private int room;

    public Reservation(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
