package guestComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class EMailType {

    private static final Pattern eMailPattern = Pattern.compile("[a-z]+[@][a-z]+[.][a-z]+");
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
}
