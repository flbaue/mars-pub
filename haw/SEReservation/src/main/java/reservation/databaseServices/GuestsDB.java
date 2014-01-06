package reservation.databaseServices;

import reservation.guestComponent.EMailType;
import reservation.guestComponent.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Bauer on 05.01.14.
 * flbaue@posteo.de
 */
public class GuestsDB implements IGuestsDB {

    private static final String GUEST_TABLE = "Guests";
    private DataBase dataBase;

    public GuestsDB(DataBase dataBase) {
        this.dataBase = dataBase;

        try {
            dataBase.connect();

            if (!this.dataBase.tableExists(GUEST_TABLE)) {

                String sql = "CREATE TABLE #TABLE# (Id INT PRIMARY KEY, Name TEXT, Email TEXT, Regular TEXT)";
                sql = sql.replace("#TABLE#", GUEST_TABLE);

                this.dataBase.execute(sql);
            }
        } finally {
            this.dataBase.close();
        }
    }

    @Override
    public synchronized int getUniqueNumber() {
        String sql = "SELECT Id FROM #TABLE# ORDER BY Id desc";
        sql = sql.replace("#TABLE#", GUEST_TABLE);

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
    public synchronized Guest getGuestByNumber(int number) {
        String sql = "SELECT * FROM #TABLE# WHERE Id = #V1#;";
        sql = sql.replace("#TABLE#", GUEST_TABLE);
        sql = sql.replace("#V1#", String.valueOf(number));

        Guest guest = null;

        try {
            dataBase.connect();
            ResultSet rs = dataBase.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                boolean regular = Boolean.parseBoolean(rs.getString("Regular"));

                guest = new Guest(id, name, new EMailType(email));
                guest.setRegularGuest(regular);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        return guest;
    }

    @Override
    public synchronized List<Guest> getGuestsByName(String name) {
        String sql = "SELECT * FROM #TABLE# WHERE Name like '%#V1#%'";
        sql = sql.replace("#TABLE#", GUEST_TABLE);
        sql = sql.replace("#V1#", name);

        List<Guest> guests = new ArrayList<>();

        try {
            dataBase.connect();
            ResultSet rs = dataBase.executeQuery(sql);

            while (rs.next()) {
                int idDB = rs.getInt("Id");
                String nameDB = rs.getString("Name");
                String emailDB = rs.getString("Email");
                boolean regularDB = Boolean.parseBoolean(rs.getString("Regular"));

                Guest guest = new Guest(idDB, nameDB, new EMailType(emailDB));
                guest.setRegularGuest(regularDB);

                guests.add(guest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        return guests;
    }

    @Override
    public synchronized List<Guest> getGuestByEMail(String eMail) {
        String sql = "SELECT * FROM #TABLE# WHERE Email like '%#V1#%'";
        sql = sql.replace("#TABLE#", GUEST_TABLE);
        sql = sql.replace("#V1#", eMail.toLowerCase());

        List<Guest> guests = new ArrayList<>();

        try {
            dataBase.connect();
            ResultSet rs = dataBase.executeQuery(sql);

            while (rs.next()) {
                int idDB = rs.getInt("Id");
                String nameDB = rs.getString("Name");
                String emailDB = rs.getString("Email");
                boolean regularDB = Boolean.parseBoolean(rs.getString("Regular"));

                Guest guest = new Guest(idDB, nameDB, new EMailType(emailDB));
                guest.setRegularGuest(regularDB);

                guests.add(guest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }

        return guests;
    }

    @Override
    public synchronized void saveGuest(Guest guest) {

        String sql = "SELECT * FROM #TABLE# WHERE Id = #V1#;";
        sql = sql.replace("#TABLE#", GUEST_TABLE);
        sql = sql.replace("#V1#", String.valueOf(guest.getNumber()));


        try {
            dataBase.connect();
            ResultSet rs = dataBase.executeQuery(sql);

            if (rs.next()) {
                // Update
                sql = "UPDATE #TABLE# SET Name = '#V1#', Email = '#V2#', Regular = '#V3#' WHERE Id = #V4#";
                sql = sql.replace("#TABLE#", GUEST_TABLE);
                sql = sql.replace("#V1#", guest.getName());
                sql = sql.replace("#V2#", guest.getEmail().toString());
                sql = sql.replace("#V3#", String.valueOf(guest.isRegularGuest()));
                sql = sql.replace("#V4#", String.valueOf(guest.getNumber()));

                dataBase.execute(sql);

            } else {
                // Insert
                sql = "INSERT INTO #TABLE# (Id, Name, Email, Regular) VALUES (#V1#, '#V2#', '#V3#', '#V4#')";
                sql = sql.replace("#TABLE#", GUEST_TABLE);
                sql = sql.replace("#V1#", String.valueOf(guest.getNumber()));
                sql = sql.replace("#V2#", guest.getName());
                sql = sql.replace("#V3#", guest.getEmail().toString());
                sql = sql.replace("#V4#", String.valueOf(guest.isRegularGuest()));

                dataBase.execute(sql);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataBase.close();
        }
    }
}
