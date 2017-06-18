package utilities;

import localities.AdministrativeZones;
import localities.Municipality;
import localities.Region;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class JDBCRunner {
    public static void start() throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");

        // Create DB connection
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:regionaldata.db")) {

            // Create Statement
            Statement statement = connection.createStatement();



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void seedDatabase() throws ClassNotFoundException {
        Map<Region, List<Municipality>> localities = new AdministrativeZones().getRegionMap();

        Class.forName("org.sqlite.JDBC");
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:regionaldata.db")) {

            // Create Statement
            Statement statement = connection.createStatement();

            // Create region table
            statement.executeUpdate("DROP TABLE IF EXISTS regions");
            statement.executeUpdate("CREATE TABLE regions(id INTEGER PRIMARY KEY, region_code STRING, name STRING)");

            // Create municipality table
            statement.executeUpdate("DROP TABLE IF EXISTS municipalities");
            statement.executeUpdate("CREATE TABLE regions(id INTEGER PRIMARY KEY, region_code STRING, municipality_code STRING, name STRING)");

            // Fill tables with content
            for (Region region : localities.keySet()) {
                insertRegion(statement, region);
                for (Municipality municipality : localities.get(region)) {
                    insertMunicipality(statement, municipality);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertRegion(Statement statement, Region region) throws SQLException {
        String sql = "INSERT INTO regions (region_code, name) VALUES ('%s', '%s')";
        sql = String.format(sql, region.getRegionCode(), region.getName());

        statement.executeUpdate(sql);
    }

    private static void insertMunicipality(Statement statement, Municipality municipality) throws SQLException {
        String sql = "INSERT INTO municipalities (region_code, municipality_code, name) VALUES ('%s', '%s', '%s')";
        sql = String.format(sql, municipality.getRegionCode(), municipality.getMunicipalityCode(), municipality.getName());

        statement.executeUpdate(sql);
    }
}
