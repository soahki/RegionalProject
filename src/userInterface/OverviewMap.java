package userInterface;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import utilities.ColorChanger;

import java.io.File;

public class OverviewMap {
    protected static StackPane getMap(String regionCode) {
        // Create Image
        File swedenFile = new File("./resources/images/Sweden_exempel.png");
        Image swedenImage = new Image(swedenFile.toURI().toString(), 300.0, 500.0, true, true);

        // Display image on screen
        StackPane map = new StackPane();

        map.getChildren().add(new ImageView(swedenImage));

        if (regionCode.length() != 0) {
            File regionFile = new File("./resources/images/regions/" + regionCode + ".png");
            map.getChildren().add(getColoredImage(regionFile, Color.BLUE));
        }
        return map;
    }

    protected static StackPane getMap() {
        return getMap("");
    }

    public static ImageView getColoredImage(File file, Color color) {
        return getColoredImage(file, color, new Dimension2D(300, 500));
    }

    public static ImageView getColoredImage(File file, Color color, Dimension2D dimension) {
        Image image = new Image(
                file.toURI().toString(), dimension.getWidth(), dimension.getHeight(), true, true);
        WritableImage writableImage = ColorChanger.colorImage(image, Color.WHITE, color);
        return new ImageView(writableImage);
    }

}
