package utilities;

import javafx.geometry.Dimension2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import localities.Municipality;
import localities.Region;

import java.io.File;
import java.util.*;

public class NumberOfMunicipalitiesFilter implements Filter {
    private Map<Region, List<Municipality>> regionMap;

    public NumberOfMunicipalitiesFilter(Map<Region, List<Municipality>> regionMap) {
        this.regionMap = regionMap;
    }

    @Override
    public StackPane getFilteredImages() {
        StackPane stackPane = new StackPane();
        //Set<ImageView> images = new HashSet<>();
        ImageView[] images = new ImageView[regionMap.keySet().size()];
        Thread[] threads = new Thread[regionMap.keySet().size()];
        int i = 0;
        for (Region region : regionMap.keySet()) {
            int index = i;
            Runnable renderer = () -> {
                File file = new File("./resources/images/regions/" + region.getRegionCode() + ".png");
                ImageView image = MapGenerator.getColoredImage(
                        file, getColorFromRegions(region), new Dimension2D(300, 500));
                //images.add(image);
                images[index] = image;
            };
            threads[i] = new Thread(renderer);
            i++;
        }
        for (Thread thread : threads) {
            thread.start();
        }

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

    public Color getColorFromRegions(Region region) {
        int red = 50 + 4 * (regionMap.get(region).size());
        if (red > 255)
            red = 255;
        return Color.rgb(red, 0, 0);
    }

}
