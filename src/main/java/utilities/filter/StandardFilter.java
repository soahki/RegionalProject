package utilities.filter;

import javafx.scene.layout.StackPane;
import statistics.Quantifiable;
import ui.tools.MapGenerator;

import java.util.HashMap;
import java.util.Map;

public class StandardFilter implements Filter, Quantifiable{
    @Override
    public StackPane getFilteredImages() {
        return MapGenerator.getMap();
    }

    @Override
    public String toString() {
        return "Regions in Sweden (default)";
    }

    @Override
    public Map<Object, Double> getStatistics() {
        Map<Object, Double> statisticsMap = new HashMap<>();
        statisticsMap.put("Sweden", 1.0);
        return statisticsMap;
    }
}
