package reservation.databaseServices;

import reservation.reservationComponent.AdditionalService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Bauer on 05.01.14.
 * flbaue@posteo.de
 */
public class AdditionalServicesDB implements IAdditionalServicesDB {

    private static final String ADDITIONAL_SERVICES_TABLE = "AdditionalServices";
    private DataBase dataBase;

    public AdditionalServicesDB(DataBase dataBase) {

        try {
            this.dataBase = dataBase;

            this.dataBase.connect();

            if (!this.dataBase.tableExists(ADDITIONAL_SERVICES_TABLE)) {

                String sql = "CREATE TABLE #TABLE# (Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Service TEXT)";
                sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);

                this.dataBase.execute(sql);
            }
        } finally {
            this.dataBase.close();
        }
    }

    @Override
    public synchronized int getUniqueNumber() {
        String sql = "SELECT Id FROM #TABLE# ORDER BY Id desc";
        sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);


        int lastId = 0;

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);

            if (rs.next()) {
                lastId = rs.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBase.close();
        }

        lastId += 1;
        return lastId;
    }

    @Override
    public synchronized void saveAdditionalService(AdditionalService additionalService) {
        String sql = "SELECT * FROM #TABLE# WHERE Id = #V1#;";
        sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);
        sql = sql.replace("#V1#", String.valueOf(additionalService.getNumber()));

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);

            if (rs.next()) {
                // Update
                sql = "UPDATE #TABLE# SET Service = '#V1#' WHERE Id = #V2#";
                sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);
                sql = sql.replace("#V1#", additionalService.getService());
                sql = sql.replace("#V2#", String.valueOf(additionalService.getNumber()));

                dataBase.execute(sql);

            } else {
                // Insert
//                sql = "INSERT INTO #TABLE# (Id, Service) VALUES (#V1#, '#V2#')";
                sql = "INSERT INTO #TABLE# (Service) VALUES ('#V2#')";

                sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);
                //sql = sql.replace("#V1#", String.valueOf(additionalService.getNumber()));
                sql = sql.replace("#V2#", additionalService.getService());

                dataBase.execute(sql);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }
    }

    @Override
    public synchronized AdditionalService getAdditionalServiceByNumber(int number) {
        String sql = "SELECT * FROM #TABLE# WHERE Id = #V1#;";
        sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);
        sql = sql.replace("#V1#", String.valueOf(number));

        AdditionalService additionalService = null;

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("Id");
                String service = rs.getString("Service");

                additionalService = new AdditionalService(id, service);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        return additionalService;
    }

    @Override
    public synchronized List<AdditionalService> getAdditionalServicesByText(String service) {
        String sql = "SELECT * FROM #TABLE# WHERE Service like '%#V1#%'";
        sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);
        sql = sql.replace("#V1#", service);


        List<AdditionalService> additionalServices = new ArrayList<>();

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);

            while (rs.next()) {
                int idDB = rs.getInt("Id");
                String serviceDB = rs.getString("Service");
                AdditionalService additionalService = new AdditionalService(idDB, serviceDB);
                additionalServices.add(additionalService);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        return additionalServices;
    }
}
