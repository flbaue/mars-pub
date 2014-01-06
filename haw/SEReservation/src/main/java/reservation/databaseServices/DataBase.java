package reservation.databaseServices;

import java.sql.*;

/**
 * Created by Florian Bauer on 05.01.14.
 * flbaue@posteo.de
 */
public class DataBase {

    private String dbURL;
    private Connection connection;

    public DataBase(String dbURL, String dbDriver) {
        this.dbURL = dbURL;
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public int getUniqueID(String table) {
        String sql = "SELECT Id FROM #TABLE# ORDER BY Id desc";
        sql = sql.replace("#TABLE#", table);


        int lastId = 0;

        try {
            connect();

            ResultSet rs = executeQuery(sql);

            if (rs.next()) {
                lastId = rs.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        lastId += 1;
        return lastId;
    }

    public boolean tableExists(String tableName) {
        boolean result = true;
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("select 1 from " + tableName);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: " + tableName)) {
                return false;
            } else {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rs;
    }

    public void execute(String query) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
