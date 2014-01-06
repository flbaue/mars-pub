package guestComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class Guest {

    private final int number;
    private String name;
    private EMailType email;
    private boolean regularGuest;

    public Guest(int number, String name, EMailType email) {
        this.number = number;
        regularGuest = false;
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
}
