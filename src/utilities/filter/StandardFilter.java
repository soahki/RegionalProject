package utilities.filter;

import javafx.scene.layout.StackPane;
import utilities.MapGenerator;

public class StandardFilter implements Filter {
    @Override
    public StackPane getFilteredImages() {
        return MapGenerator.getMap();
    }

    @Override
    public String toString() {
        return "Regioner i Sverige (inget filter)";
    }
}
