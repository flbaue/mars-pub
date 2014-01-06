package reservation.guestComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class EMailType {

    private static final Pattern eMailPattern = Pattern.compile("[a-zA-Z0-9]+[@][a-z]+[.][a-z]{2,}");
    private final String eMail;

    public EMailType(String eMail) {
        Matcher matcher = eMailPattern.matcher(eMail);
        if (matcher.matches()) {
            this.eMail = eMail;
        } else {
            throw new IllegalArgumentException("E-Mail is not in a valid form");
        }
    }

    @Override
    public String toString() {
        return eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EMailType eMailType = (EMailType) o;

        if (!eMail.equals(eMailType.eMail)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return eMail.hashCode();
    }
}
