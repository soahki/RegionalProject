import localities.Entity;
import statistics.Population;
import statistics.Statistics;
import ui.UserInterface;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // UserInterface.launchUI(args);

        Statistics stats = Population.getStatistics();
        Object minObj = stats.getValuesMap().get(stats.getMin());
        String minName = null;
        if (minObj instanceof Entity) {
            minName = ((Entity) minObj).getName();
        }
        Object maxObj = stats.getValuesMap().get(stats.getMax());
        String maxName = null;
        if (maxObj instanceof Entity) {
            maxName = ((Entity) maxObj).getName();
        }
        System.out.println(stats.getName());
        System.out.printf("Min: %d (%s)%n", (int)stats.getMin(), minName);
        System.out.printf("Max: %d (%s)%n", (int)stats.getMax(), maxName);
        System.out.printf("Mean: %.2f%n", stats.getMean());
        System.out.printf("Median: %.2f%n", stats.getMedian());
        System.out.printf("Sum: %d%n", (int)stats.getSum());
        System.out.printf("StdDev: %.2f%n", stats.getStdDev());
        System.out.printf("Cases: %d%n", (int)stats.getN());

    }
}
