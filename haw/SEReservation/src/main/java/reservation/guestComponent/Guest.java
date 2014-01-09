package reservation.guestComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class Guest {

    private int number;
    private String name;
    private EMailType email;
    private boolean regularGuest;

    public Guest(String name, EMailType email) {
        this.number = -1;
        this.name = name;
        this.email = email;
        regularGuest = false;
    }

    public void setNumber(int number){
        if(this.number == -1) {
            this.number = number;
        }
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EMailType getEmail() {
        return email;
    }

    public void setEmail(EMailType email) {
        this.email = email;
    }

    public boolean isRegularGuest() {
        return regularGuest;
    }

    public void setRegularGuest(boolean regularGuest) {
        this.regularGuest = regularGuest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        if (number != guest.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
