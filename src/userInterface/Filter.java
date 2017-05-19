package userInterface;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public interface Filter {
    StackPane getFilteredImages(Image[] images);
}