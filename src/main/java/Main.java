import statistics.Population;
import statistics.Statistics;
import ui.UserInterface;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // UserInterface.launchUI(args);

        Statistics stats = Population.getStatistics();
        System.out.println(stats.getName());
        System.out.printf("Min: %d (%s)%n", (int)stats.getMin(), stats.getValuesMap().get(stats.getMin()).getName());
        System.out.printf("Max: %d (%s)%n", (int)stats.getMax(), stats.getValuesMap().get(stats.getMax()).getName());
        System.out.printf("Mean: %.2f%n", stats.getMean());
        System.out.printf("Median: %.2f%n", stats.getMedian());
        System.out.printf("Sum: %d%n", (int)stats.getSum());
        System.out.printf("StdDev: %.2f%n", stats.getStdDev());
        System.out.printf("Cases: %d%n", (int)stats.getN());

    }
}
