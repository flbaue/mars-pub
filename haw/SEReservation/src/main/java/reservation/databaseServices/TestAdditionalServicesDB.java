package reservation.databaseServices;

import reservation.reservationComponent.AdditionalService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Bauer on 04.01.14.
 * flbaue@posteo.de
 */
public class TestAdditionalServicesDB implements IAdditionalServicesDB {

    private List<AdditionalService> additionalServices;

    public TestAdditionalServicesDB() {
        additionalServices = new ArrayList<>();
        additionalServices.add(new AdditionalService(getUniqueNumber(), "WLAN"));
        additionalServices.add(new AdditionalService(getUniqueNumber(), "Sauna"));
        additionalServices.add(new AdditionalService(getUniqueNumber(), "Vollpension"));
        additionalServices.add(new AdditionalService(getUniqueNumber(), "MiniBar"));
    }

    @Override
    public int getUniqueNumber() {
        return additionalServices.size() + 1;
    }

    @Override
    public void saveAdditionalService(AdditionalService additionalService) {
        additionalServices.remove(additionalService);
        additionalServices.add(additionalService);
    }

    @Override
    public AdditionalService getAdditionalServiceByNumber(int number) {
        AdditionalService additionalService = null;
        for(AdditionalService service : additionalServices) {
            if(service.getNumber() == number) {
                additionalService = service;
                break;
            }
        }
        return additionalService;
    }

    @Override
    public List<AdditionalService> getAdditionalServicesByText(String service) {
        List<AdditionalService> results = new ArrayList<>();
        for(AdditionalService additionalService : additionalServices) {
            if(additionalService.getService().toLowerCase().contains(service.toLowerCase())) {
                results.add(additionalService);
            }
        }
        return results;
    }
}
