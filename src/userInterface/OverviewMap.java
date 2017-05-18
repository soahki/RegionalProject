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
        // Create Image
        File swedenFile = new File("./resources/images/Sweden_exempel.png");
        Image swedenImage = new Image(swedenFile.toURI().toString(), 300.0, 500.0, true, true);

        // Display image on screen
        StackPane map = new StackPane();
        map.getChildren().add(new ImageView(swedenImage));

        if (regionCode.length() != 0) {
            File regionFile = new File("./resources/images/regions/" + regionCode + ".png");
            Image regionImage = new Image(regionFile.toURI().toString(), 300.0, 500.0, true, true);
            WritableImage wImage = ColorChanger.colorImage(regionImage, Color.WHITE, Color.BLUE);
            map.getChildren().add(new ImageView(wImage));
        }
        return map;
    }

    static StackPane getMap() {
        return getMap("");
    }

}
