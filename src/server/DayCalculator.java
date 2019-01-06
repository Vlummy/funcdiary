package server;

import java.time.Month;
import java.util.Map;

public abstract class DayCalculator {

    public static double average(DaysCollection collection) {
        Map<String, Day> map = collection.getDayCollection();
        return map.entrySet()
                .stream()
                .mapToDouble(day -> day.getValue().getRating())
                .average()
                .orElse(0);
    }

    public static double averageByMonth(DaysCollection collection, Month month, int year) {
        Map<String, Day> map = collection.getDayCollection();
        return map.entrySet()
                .stream()
                .filter(day -> day.getValue().getDate().getMonth().equals(month))
                .filter(day -> day.getValue().getDate().getYear() == year)
                .mapToDouble(day -> day.getValue().getRating())
                .average()
                .orElse(0);
    }

    public static int count(DaysCollection collection) {
        Map<String, Day> map = collection.getDayCollection();
        return (int) map.entrySet()
                .stream()
                .count();
    }
}
