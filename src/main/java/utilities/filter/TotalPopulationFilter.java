package utilities.filter;

import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import localities.Municipality;
import localities.Region;
import utilities.jdbc.IO;
import ui.tools.MapGenerator;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TotalPopulationFilter implements Filter {
    private Map<Region, List<Municipality>> regionMap;

    public TotalPopulationFilter(Map<Region, List<Municipality>> regionMap) {
        this.regionMap = regionMap;
    }

    @Override
    public StackPane getFilteredImages()  {
        StackPane stackPane = MapGenerator.getMap();
        ImageView[] images = new ImageView[regionMap.keySet().size()];
        Thread[] threads = new Thread[regionMap.keySet().size()];
        int i = 0;
        for (Region region : regionMap.keySet()) {
            int index = i;  // Appoint each Runnable a unique number where its rendered image will be inserted in the ImageView array.
            Runnable renderer = () -> {
                File file = new File("./src/main/resources/images/regions/" + region.getRegionCode() + ".png");
                ImageView image = MapGenerator.getColoredImage(
                        file, getColorFromRegions(region), new Dimension2D(300, 500));
                images[index] = image;
            };
            threads[i] = new Thread(renderer);
            i++;
        }
        for (Thread thread : threads) {
            thread.start();
        }

        // Assure that all images are rendered before adding them to a StackPane.
        while (true) {
            int numRunning = 0;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    numRunning++;
                }
            }

            if (numRunning == 0) {
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stackPane.getChildren().addAll(images);
        return stackPane;
    }

    private Color getColorFromRegions(Region region) {
        int red = 0;
        try {
            red = numOfPeopleInRegion(region) / 7000;
            if (red > 255)
                red = 255;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Color.rgb(red, 0, 0);
    }

    private int numOfPeopleInRegion(Region region) throws SQLException {
        int total = 0;
        for (Municipality municipality : regionMap.get(region)) {
            total += numOfPeopleInMunicipality(municipality);
        }
        return total;
    }

    private int numOfPeopleInMunicipality(Municipality municipality) throws SQLException {
        IO io = new IO();
        String query = "SELECT y2016 FROM population WHERE municipality_code = %s";
        String sql = String.format(query, municipality.getMunicipalityCode());
        ResultSet rs = io.retrieve(sql);
        return rs.getInt("y2016");
    }

    @Override
    public String toString() {
        return "Filter: Befolkning";
    }
}
