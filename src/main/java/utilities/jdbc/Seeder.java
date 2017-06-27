package utilities.jdbc;

import utilities.parser.TextParser;
import localities.Municipality;
import localities.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Only utilize this class if no database is yet initialized.
 */
public class Seeder {
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
        Map<Region, List<Municipality>> localities = new TextParser().getRegionMap();

        Class.forName("org.sqlite.JDBC");
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:regionaldata.db")) {

            // Create Statement
            Statement statement = connection.createStatement();

            // Create region table
            statement.executeUpdate("DROP TABLE IF EXISTS regions");
            statement.executeUpdate("CREATE TABLE regions(region_code STRING PRIMARY KEY, name STRING)");

            // Create municipality table
            statement.executeUpdate("DROP TABLE IF EXISTS municipalities");
            statement.executeUpdate("CREATE TABLE municipalities(municipality_code STRING PRIMARY KEY, region_code STRING, name STRING, FOREIGN KEY(region_code) REFERENCES regions(region_code))");

            // Fill tables with content
            for (Region region : localities.keySet()) {
                System.out.println(region.getName());
                insertRegion(statement, region);
                for (Municipality municipality : localities.get(region)) {
                    insertMunicipality(statement, municipality);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Map<Region, List<Municipality>> getMap() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        Map<Region, List<Municipality>> regionMap = new HashMap<>();

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:regionaldata.db")) {
            Statement statement = connection.createStatement();

            List<Region> regions = getRegions(statement);
            List<Municipality> municipalities = getMunicipalities(statement);

            // Pair municipalities with regions in a map
            for (Region region : regions) {
                regionMap.put(region, new ArrayList<>());
                for (Municipality municipality : municipalities) {
                    if (region.getRegionCode().equals(municipality.getRegionCode())){
                        regionMap.get(region).add(municipality);
                    }
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return regionMap;
    }

    private static List<Region> getRegions(Statement statement) throws SQLException {
        List<Region> regions = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM regions");
        while (rs.next()) {
            String name = rs.getString("name");
            String regionCode = rs.getString("region_code");
            regions.add(new Region(name, regionCode));
        }
        return regions;
    }

    private static List<Municipality> getMunicipalities(Statement statement) throws SQLException {
        List<Municipality> municipalities = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM municipalities");
        while (rs.next()) {
            String name = rs.getString("name");
            String regionCode = rs.getString("region_code");
            String municipalityCode = rs.getString("municipality_code");
            municipalities.add(new Municipality(name, municipalityCode, regionCode));
        }
        return municipalities;
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
