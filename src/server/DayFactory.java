package server;

public class DayFactory {
    public static Day createDay(DayType type) {
        Day day = null;
        switch (type) {
            case SINGLE:
                day = new Day();
                break;
            case PERIOD:
                day = new Day();
                break;
        }
        return day;
    }
}
