package userInterface;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import localities.Municipality;
import localities.Region;

import java.io.File;
import java.util.List;
import java.util.Map;

public class NumberOfMunicipalitiesFilter implements Filter {
    private Map<Region, List<Municipality>> regionMap;

    public NumberOfMunicipalitiesFilter(Map<Region, List<Municipality>> regionMap) {
        this.regionMap = regionMap;
    }


    @Override
    public StackPane getFilteredImages() {
        StackPane stackPane = new StackPane();
        for (Region region : regionMap.keySet()) {
            File file = new File("./resources/images/regions/" + region.getRegionCode() + ".png");
            ImageView image = OverviewMap.getColoredImage(
                    file, getColorFromRegions(region), new Dimension2D(300, 500));
            stackPane.getChildren().add(image);
        }
        return stackPane;
    }

    public Color getColorFromRegions(Region region) {
        int red = 0;
        red = 50 + 4 * (regionMap.get(region).size());
        if (red > 255)
            red = 255;
        return Color.rgb(red, 0, 0);
    }

}
