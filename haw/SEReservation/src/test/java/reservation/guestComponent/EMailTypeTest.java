package reservation.guestComponent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class EMailTypeTest {

    @Test
    public void testToString() throws Exception {
        String address = "abc@def.ghi";
        EMailType email = new EMailType(address);
        assertEquals(address, email.toString());
    }

    @Test
    public void testValidation() throws Exception {

        List<String> addresses = new ArrayList<>();

        addresses.add("abc$def.ghi");
        addresses.add("abc@def/ghi");
        addresses.add("@def.ghi");
        addresses.add("abc@def");

        for (String address : addresses) {
            EMailType email = null;
            try {
                email = new EMailType(address);
            } catch (IllegalArgumentException ex) {
                assertEquals("E-Mail is not in a valid form", ex.getMessage());
            }
            assertNull(email);
        }
    }
}
