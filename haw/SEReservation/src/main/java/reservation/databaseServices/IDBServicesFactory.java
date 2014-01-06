package reservation.databaseServices;

/**
 * Created by Florian Bauer on 05.01.14.
 * flbaue@posteo.de
 */
public interface IDBServicesFactory {

    IGuestsDB getGuestsDB();

    IReservationsDB getReservationsDB();

    IAdditionalServicesDB getAdditionalServicesDB();
}
