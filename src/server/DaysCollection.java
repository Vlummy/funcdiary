package server;

import java.io.Serializable;
import java.util.HashMap;

public class DaysCollection implements Serializable {
    private HashMap<String, Day> dayCollection;

    public DaysCollection() {
        this.dayCollection = new HashMap<>();
    }

    public DaysCollection(HashMap<String, Day> dayCollection) {
        this.dayCollection = dayCollection;
    }

    public void addDay(String date, Day day) {
        dayCollection.put(date, day);
    }

    public Day getDay(String date) {
        Day day = dayCollection.get(date);
        return day;
    }

    public HashMap<String, Day> getDayCollection() {
        return this.dayCollection;
    }
}
