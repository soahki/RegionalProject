package userInterface;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import localities.Municipality;
import localities.Region;

import java.util.Map;

public class NumberOfMunicipalitiesFilter implements Filter {
    private Map<Region, Municipality> regionMap;

    public NumberOfMunicipalitiesFilter(Map<Region, Municipality> regionMap) {
        this.regionMap = regionMap;
    }


    @Override
    public StackPane getFilteredImages(Image[] images) {
        return null;
    }

}
