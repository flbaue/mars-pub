package reservation.databaseServices;

import org.junit.BeforeClass;
import org.junit.Test;
import reservation.reservationComponent.AdditionalService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Florian Bauer on 06.01.14.
 * flbaue@posteo.de
 */
public class AdditionalServicesDBTest {

    private static IDBServicesFactory servicesFactory;
    private static IAdditionalServicesDB additionalServicesDB;


    @BeforeClass
    public static void setUp() throws Exception {
        servicesFactory = new DBServicesFactory(DBServicesFactory.DATABASE_ENVIRONMENT, "org.sqlite.JDBC", "jdbc:sqlite:AdditionalServicesDBTest.db");
        additionalServicesDB = servicesFactory.getAdditionalServicesDB();
        AdditionalService service1 = new AdditionalService(additionalServicesDB.getUniqueNumber(), "WLAN");
        additionalServicesDB.saveAdditionalService(service1);
    }

    @Test
    public void testGetUniqueNumber() throws Exception {
        int id = additionalServicesDB.getUniqueNumber();
        AdditionalService service = additionalServicesDB.getAdditionalServiceByNumber(id);
        assertNull(service);
    }

    @Test
    public void testSaveAdditionalService() throws Exception {
        AdditionalService service = new AdditionalService(additionalServicesDB.getUniqueNumber(),"Schläge");
        additionalServicesDB.saveAdditionalService(service);
        additionalServicesDB.getAdditionalServicesByText("Schläge");
    }

    @Test
    public void testGetAdditionalServiceByNumber() throws Exception {
        AdditionalService service = additionalServicesDB.getAdditionalServiceByNumber(1);
        assertNotNull(service);
    }

    @Test
    public void testGetAdditionalServicesByText() throws Exception {
        List<AdditionalService> services = additionalServicesDB.getAdditionalServicesByText("WLAN");
        assertNotNull(services);
        assertEquals(1, services.size());
        assertEquals("WLAN", services.get(0).getService());
    }
}
