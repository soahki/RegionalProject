package utilities.jdbc;

import java.sql.*;

public class IO {
    private Connection connection;
    private Statement statement;
    private final static String DATABASE_CLASS = "org.sqlite.JDBC";
    private final static String DATABASE_SOURCE = "jdbc:sqlite:regionaldata.db";

    public IO() {
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

    /**
     * This method is used to create tables or insert data. Use SQL statements, not of type SELECT.
     * @param sql String that adhers to SQL statement syntax.
     * @throws SQLException
     */
    public void insert(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    /**
     * This method retrieves data from database. Use SQL statements of type SELECT.
     * @param sql String that adhers to SQL syntax.
     * @return
     * @throws SQLException
     */
    public ResultSet retrieve(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public void close() throws SQLException {
        connection.close();
    }
}
