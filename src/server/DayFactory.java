package server;

public abstract class DayFactory {
    public static Day createDay(DayType type) {
        Day day = null;
        switch (type) {
            case BAD:
                day = new Day("Bad");
                break;
            case SAD:
                day = new Day("Sad");
                break;
            case NEUTRAL:
                day = new Day("Neutral");
                break;
            case GOOD:
                day = new Day("Good");
                break;
            case SURPRISING:
                day = new Day("Surprising");
                break;
            case BORING:
                day = new Day("Boring");
                break;
            case EXCITING:
                day = new Day("Exciting");
                break;
            case LONELY:
                day = new Day("Lonely");
                break;
            case MINDFUL:
                day = new Day("Mindful");
                break;
            case PRODUCTIVE:
                day = new Day("Productive");
                break;
            case SCARY:
                day = new Day("Scary");
                break;
            case STRESSFUL:
                day = new Day("Stressful");
                break;
        }
        return day;
    }
}
