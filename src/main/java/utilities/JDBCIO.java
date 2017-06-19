package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCIO {
    private Connection connection;
    private Statement statement;
    private final static String DATABASE_CLASS = "org.sqlite.JDBC";
    private final static String DATABASE_SOURCE = "jdbc:sqlite:regionaldata.db";

    public JDBCIO() {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws ClassNotFoundException {
        Class.forName(DATABASE_CLASS);

        try {
            connection = DriverManager.getConnection(DATABASE_SOURCE);
            statement = connection.createStatement();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getMunicipalityData(String municipalityCode) {
        String result = "";
        return result;
    }
}
