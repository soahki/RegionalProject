package statistics;

import localities.Entity;
import localities.Municipality;
import utilities.jdbc.IO;
import utilities.jdbc.Seeder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Population {
    private static Map<Object, Double> popMap = new HashMap<>();

    public static Statistics getStatistics() {
        List<Municipality> municipalities;
        try {
            municipalities = Seeder.getMunicipalities(Seeder.getStatement());

            for (Municipality municipality : municipalities) {
                popMap.put(municipality, (double) numOfPeopleInMunicipality(municipality));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Statistics("Population in municipalities", popMap);
    }

    private static int numOfPeopleInMunicipality(Municipality municipality) throws SQLException {
        IO io = new IO();
        String query = "SELECT y2016 FROM population WHERE municipality_code = %s";
        String sql = String.format(query, municipality.getMunicipalityCode());
        ResultSet rs = io.retrieve(sql);
        return rs.getInt("y2016");
    }
}
