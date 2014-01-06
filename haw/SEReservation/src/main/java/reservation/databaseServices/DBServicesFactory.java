package reservation.databaseServices;

/**
 * Created by Florian Bauer on 05.01.14.
 * flbaue@posteo.de
 */
public class DBServicesFactory implements IDBServicesFactory {

    public static final int TEST_ENVIRONMENT = 0;
    public static final int DATABASE_ENVIRONMENT = 1;
    private final int environment;
    private IGuestsDB guestsDB;
    private IReservationsDB reservationsDB;
    private IAdditionalServicesDB additionalServicesDB;
    private DataBase dataBase;

    public DBServicesFactory(int environment, String dbDriver, String dbURL) {
        this.environment = environment;
        if (this.environment == DATABASE_ENVIRONMENT) {
            dataBase = new DataBase(dbURL, dbDriver);
        }
    }

    @Override
    public IGuestsDB getGuestsDB() {
        switch (environment) {
            case TEST_ENVIRONMENT:
                if (guestsDB == null) {
                    guestsDB = new TestGuestsDB();
                }
                return guestsDB;
            case DATABASE_ENVIRONMENT:
                if (guestsDB == null) {
                    guestsDB = new GuestsDB(dataBase);
                }
                return guestsDB;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public IReservationsDB getReservationsDB() {
        switch (environment) {
            case TEST_ENVIRONMENT:
                if (reservationsDB == null) {
                    reservationsDB = new TestReservationsDB();
                }
                return reservationsDB;
            case DATABASE_ENVIRONMENT:
                if (reservationsDB == null) {
                    reservationsDB = new ReservationsDB(dataBase);
                }
                return reservationsDB;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public IAdditionalServicesDB getAdditionalServicesDB() {
        switch (environment) {
            case TEST_ENVIRONMENT:
                if (additionalServicesDB == null) {
                    additionalServicesDB = new TestAdditionalServicesDB();
                }
                return additionalServicesDB;
            case DATABASE_ENVIRONMENT:
                if (additionalServicesDB == null) {
                    additionalServicesDB = new AdditionalServicesDB(dataBase);
                }
                return additionalServicesDB;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
