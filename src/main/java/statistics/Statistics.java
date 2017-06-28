package statistics;


import java.util.*;

public class Statistics {
    private String name;
    private Map<Object, Double> statisticsMap;
    private Map<Double, Object> valuesMap;
    private List<Double> valueList;

    public Statistics(String name, Map<Object, Double> statisticsMap) {
        this.name = name;
        this.statisticsMap = statisticsMap;
        initializeValues();

    }

    private void initializeValues() {
        valuesMap = new HashMap<>();
        for (Object object : statisticsMap.keySet()) {
            valuesMap.put(statisticsMap.get(object), object);
        }
        valueList = new ArrayList<>(valuesMap.keySet());
        Collections.sort(valueList);
    }

    public double getStdDev() {
        double sumDev = 0;
        for (double value : valueList) {
            sumDev += Math.pow((value - getMean()), 2);
        }
        return Math.sqrt(sumDev / getN());
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
            double medianTwo = valueList.get(medianIndex);
            if (valueList.size() > medianIndex + 1) {
                medianTwo = valueList.get(medianIndex + 1);
            }
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
        for (Object object : statisticsMap.keySet()) {
            sum += statisticsMap.get(object);
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

    public Map<Object, Double> getStatisticsMap() {
        return statisticsMap;
    }

    public Map<Double, Object> getValuesMap() {
        return valuesMap;
    }
}
