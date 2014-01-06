package reservation.databaseServices;

import reservation.reservationComponent.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Bauer on 05.01.14.
 * flbaue@posteo.de
 */
public class ReservationsDB implements IReservationsDB {

    private static final String RESERVATIONS_TABLE = "Reservations";
    private static final String ADDITIONAL_SERVICES_TABLE = "Reservations";
    private DataBase dataBase;

    public ReservationsDB(DataBase dataBase) {
        this.dataBase = dataBase;

        // Table Reservation
        try {
            this.dataBase.connect();

            if (!this.dataBase.tableExists(RESERVATIONS_TABLE)) {

                String sql = "CREATE TABLE #TABLE# (Id INT PRIMARY KEY, Guest INT, Room INT)";
                sql = sql.replace("#TABLE#", RESERVATIONS_TABLE);

                this.dataBase.execute(sql);
            }
        } finally {
            this.dataBase.close();
        }

        // Table Reservation <-> Additional Services
        try {
            this.dataBase.connect();

            if (!this.dataBase.tableExists(ADDITIONAL_SERVICES_TABLE)) {

                String sql = "CREATE TABLE #TABLE# (Id INT PRIMARY KEY, Reservation INT, Service INT, " +
                        "FOREIGN KEY(Reservation) REFERENCES #T1#(Id), FOREIGN KEY(Service) REFERENCES #T2#(Id))";
                sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);
                sql = sql.replace("#T1#", RESERVATIONS_TABLE);
                sql = sql.replace("#T2#", ADDITIONAL_SERVICES_TABLE);

                this.dataBase.execute(sql);
            }
        } finally {
            this.dataBase.close();
        }
    }

    @Override
    public synchronized int getUniqueNumber() {
        return dataBase.getUniqueID(RESERVATIONS_TABLE);
    }

    @Override
    public synchronized void saveReservation(Reservation reservation) {
        String sql = "SELECT * FROM #TABLE# WHERE Id = #V1#;";
        sql = sql.replace("#TABLE#", RESERVATIONS_TABLE);
        sql = sql.replace("#V1#", String.valueOf(reservation.getNumber()));

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);
            boolean results = rs.next();
            dataBase.close();

            if (results) {
                // Update
                sql = "UPDATE #TABLE# SET Guest = #V1#, Room = #V2# WHERE Id = #V3#";
                sql = sql.replace("#TABLE#", RESERVATIONS_TABLE);
                sql = sql.replace("#V1#", String.valueOf(reservation.getGuestNumber()));
                sql = sql.replace("#V2#", String.valueOf(reservation.getRoomNumber()));
                sql = sql.replace("#V3#", String.valueOf(reservation.getNumber()));

                dataBase.connect();
                dataBase.execute(sql);
                dataBase.close();

                //TODO update additional services

            } else {
                // Insert
                sql = "INSERT INTO #TABLE# (Id, Guest, Room) VALUES (#V1#, #V2#, #V3#)";
                sql = sql.replace("#TABLE#", RESERVATIONS_TABLE);
                sql = sql.replace("#V1#", String.valueOf(reservation.getNumber()));
                sql = sql.replace("#V2#", String.valueOf(reservation.getGuestNumber()));
                sql = sql.replace("#V3#", String.valueOf(reservation.getRoomNumber()));

                dataBase.connect();
                dataBase.execute(sql);
                dataBase.close();

                //TODO save additional services
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }
    }

    private void saveAdditionalServices(Reservation reservation) {
        try {
            dataBase.connect();
            for(int serviceID: reservation.getAdditionalServices()){
                String sql = "INSERT INTO #TABLE# (Id,Reservation,Service) Values(#V1#,#V2#,#V3#)";
                sql.replace("#TABLE",ADDITIONAL_SERVICES_TABLE);
                sql.replace("#V1#",String.valueOf(1));
                sql.replace("#V2#",String.valueOf(reservation.getNumber()));
                sql.replace("#V3#",String.valueOf(serviceID));
            }

        } catch  (SQLException ex) {
            ex.printStackTrace();
        } finally {

            dataBase.close();
        }
    }

    @Override
    public synchronized Reservation getReservationByNumber(int number) {
        String sql = "SELECT * FROM #TABLE# WHERE Id = #V1#;";
        sql = sql.replace("#TABLE#", RESERVATIONS_TABLE);
        sql = sql.replace("#V1#", String.valueOf(number));

        Reservation reservation = null;

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("Id");
                int guestNumber = rs.getInt("Guest");
                int roomNumber = rs.getInt("Room");

                reservation = new Reservation(id, guestNumber, roomNumber);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        getAdditionalServices(reservation);

        return reservation;
    }

    @Override
    public synchronized List<Reservation> getReservationsByRoomNumber(int number) {
        String sql = "SELECT * FROM #TABLE# WHERE Room = #V1#;";
        sql = sql.replace("#TABLE#", RESERVATIONS_TABLE);
        sql = sql.replace("#V1#", String.valueOf(number));

        List<Reservation> reservations = new ArrayList<>();

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                int guestNumber = rs.getInt("Guest");
                int roomNumber = rs.getInt("Room");

                Reservation reservation = new Reservation(id, guestNumber, roomNumber);
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        getAdditionalServices(reservations);

        return reservations;
    }

    @Override
    public synchronized List<Reservation> getReservationsByGuestNumber(int number) {
        String sql = "SELECT * FROM #TABLE# WHERE Guest = #V1#;";
        sql = sql.replace("#TABLE#", RESERVATIONS_TABLE);
        sql = sql.replace("#V1#", String.valueOf(number));

        List<Reservation> reservations = new ArrayList<>();

        try {
            dataBase.connect();

            ResultSet rs = dataBase.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                int guestNumber = rs.getInt("Guest");
                int roomNumber = rs.getInt("Room");

                Reservation reservation = new Reservation(id, guestNumber, roomNumber);
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        getAdditionalServices(reservations);

        return reservations;
    }

    private void getAdditionalServices(Reservation reservation) {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        getAdditionalServices(reservations);
    }

    private void getAdditionalServices(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            try {
                String sql = "SELECT * FROM #TABLE# WHERE Reservation = #V1#";
                sql = sql.replace("#TABLE#", ADDITIONAL_SERVICES_TABLE);
                sql = sql.replace("#V1#", String.valueOf(reservation.getNumber()));

                dataBase.connect();
                ResultSet rs = dataBase.executeQuery(sql);

                while (rs.next()) {
                    int serviceID = rs.getInt("Service");
                    reservation.addAdditionalService(serviceID);
                }

                dataBase.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                dataBase.close();
            }
        }
    }
}
