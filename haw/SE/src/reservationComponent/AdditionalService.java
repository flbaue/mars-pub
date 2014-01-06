package reservationComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class AdditionalService {

    private final int number;
    private String service;

    public AdditionalService(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
