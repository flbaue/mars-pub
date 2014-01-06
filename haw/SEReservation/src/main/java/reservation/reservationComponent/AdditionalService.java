package reservation.reservationComponent;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class AdditionalService {

    private final int number;
    private String service;

    public AdditionalService(int number, String service) {
        this.number = number;
        this.service = service;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdditionalService that = (AdditionalService) o;

        if (number != that.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
