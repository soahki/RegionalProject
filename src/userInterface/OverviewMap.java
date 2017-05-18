package userInterface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import utilities.ColorChanger;

import java.io.File;

class OverviewMap {
    static StackPane getMap(String regionCode) {
        // Create Image and ImageView objects
        File swedenFile = new File("./resources/images/Sweden_exempel.png");
        File regionFile = new File("./resources/images/regions/" + regionCode + ".png");

        Image swedenImage = new Image(swedenFile.toURI().toString(), 300.0, 500.0, true, true);
        Image abImage = new Image(regionFile.toURI().toString(), 300.0, 500.0, true, true);
        WritableImage wImage = ColorChanger.colorImage(abImage, Color.WHITE, Color.BLUE);

        // Display image on screen
        StackPane map = new StackPane();
        map.getChildren().add(new ImageView(swedenImage));
        map.getChildren().add(new ImageView(wImage));
        return map;
    }

}
