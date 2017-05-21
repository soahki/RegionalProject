package utilities;

import javafx.scene.layout.StackPane;

public class StandardFilter implements Filter{
    @Override
    public StackPane getFilteredImages() {
        return MapGenerator.getMap();
    }

    @Override
    public String toString() {
        return "Regioner i Sverige (inget filter)";
    }
}
