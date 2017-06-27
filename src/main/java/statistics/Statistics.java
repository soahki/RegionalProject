package statistics;

import localities.Entity;

import java.util.*;

public class Statistics {
    private String name;
    private Map<Entity, Double> statisticsMap;
    private Map<Double, Entity> valuesMap;
    private List<Double> valueList;

    public Statistics(String name, Map<Entity, Double> statisticsMap) {
        this.name = name;
        this.statisticsMap = statisticsMap;
        initializeValues();

    }

    private void initializeValues() {
        valuesMap = new HashMap<>();
        for (Entity entity : statisticsMap.keySet()) {
            valuesMap.put(statisticsMap.get(entity), entity);
        }
        valueList = new ArrayList<>(valuesMap.keySet());
        Collections.sort(valueList);
    }

    public double getStdDev() {
        double sumDev = 0;
        for (double value : valueList) {
            sumDev += Math.pow((value - getMean()), 2);
        }
        double stdDev = Math.sqrt(sumDev / getN());
        return stdDev;
    }

    public double getMin() {
        return valueList.get(0);
    }

    public double getMax() {
        return valueList.get(valueList.size()-1);
    }

    public double getMedian() {
        int medianIndex = valueList.size() / 2;
        double median;
        if (valueList.size() % 2 == 0) {
            median = valueList.get(medianIndex);
        } else {
            double medianOne = valueList.get(medianIndex);
            double medianTwo = valueList.get(medianIndex + 1);
            median = (medianOne + medianTwo) / 2;
        }

        return median;
    }

    /**
     * @return mean for entries of entities
     */
    public double getMean() {
        return getSum()/getN();
    }

    /**
     * @return sum of value for all entities
     */
    public double getSum() {
        double sum = 0;
        for (Entity entity : statisticsMap.keySet()) {
            sum += statisticsMap.get(entity);
        }
        return sum;
    }

    /**
     * @return number of entries (n)
     */
    public double getN() {
        return statisticsMap.keySet().size();
    }

    public String getName() {
        return name;
    }

    public Map<Entity, Double> getStatisticsMap() {
        return statisticsMap;
    }

    public Map<Double, Entity> getValuesMap() {
        return valuesMap;
    }
}
