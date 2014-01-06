package reservation.databaseServices;

import reservation.reservationComponent.AdditionalService;

import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public interface IAdditionalServicesDB {

    int getUniqueNumber();

    void saveAdditionalService(AdditionalService additionalService); //TODO throws exception!!

    AdditionalService getAdditionalServiceByNumber(int number);

    List<AdditionalService> getAdditionalServicesByText(String service);
}
