package utilities;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageWriter;

public class ColorChanger {

    public static WritableImage colorImage(Image image, Color originalColor, Color newColor) {

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader reader = image.getPixelReader();

        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter writer = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (reader.getColor(x, y).equals(originalColor)) {
                    writer.setColor(x, y, newColor);
                } else {
                    writer.setColor(x, y, reader.getColor(x, y));
                }
            }
        }
        return writableImage;
    }
}
