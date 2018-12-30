package server;

import java.time.Month;
import java.util.Map;

public abstract class DayCalculator {

    public static double average(DaysCollection collection, DayType daytype) {
        String type = getDayTypeAsString(daytype);
        Map<String, Day> map = collection.getDayCollection();
        return map.entrySet()
                .stream()
                .filter(day -> day.getValue().getType().equals(type))
                .mapToDouble(day -> day.getValue().getRating())
                .average()
                .orElse(0);
    }

    public static double averageByMonth(DaysCollection collection, DayType daytype, Month month, int year) {
        String type = getDayTypeAsString(daytype);
        Map<String, Day> map = collection.getDayCollection();
        return map.entrySet()
                .stream()
                .filter(day -> day.getValue().getType().equals(type))
                .filter(day -> day.getValue().getDate().getMonth().equals(month))
                .filter(day -> day.getValue().getDate().getYear() == year)
                .mapToDouble(day -> day.getValue().getRating())
                .average()
                .orElse(0);
    }

    private static String getDayTypeAsString(DayType daytype) {
        String type = "";
        switch (daytype) {
            case BAD:
                type = "Bad";
                break;
            case SAD:
                type = "Sad";
                break;
            case NEUTRAL:
                type = "Neutral";
                break;
            case GOOD:
                type = "Good";
                break;
            case SURPRISING:
                type = "Surprising";
                break;
            case BORING:
                type = "Boring";
                break;
            case EXCITING:
                type = "Exciting";
                break;
            case LONELY:
                type = "Lonely";
                break;
            case MINDFUL:
                type = "Mindful";
                break;
            case PRODUCTIVE:
                type = "Productive";
                break;
            case SCARY:
                type = "Scary";
                break;
            case STRESSFUL:
                type = "Stressful";
                break;
        }
        return type;
    }

    public static int count(DaysCollection collection, DayType daytype) {
        String type = getDayTypeAsString(daytype);
        Map<String, Day> map = collection.getDayCollection();
        return (int) map.entrySet()
                .stream()
                .filter(day -> day.getValue().getType().equals(type))
                .count();
    }
}
