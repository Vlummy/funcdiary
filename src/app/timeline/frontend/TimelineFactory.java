package app.timeline.frontend;

/**
 * TimelineFactory: Creates a app.timeline instance
 * Example: <TimelineFactory fx:factory: "instance" />
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public class TimelineFactory {
    public static TimelineView instanceDefaultStyle()
    {
        return new TimelineView(new FrontCardView(), new BackCardView());
    }

    public static TimelineView instanceNoStyle()
    {
        TimelineView timelineView = new TimelineView(new FrontCardView(), new BackCardView());
        timelineView.removeStyleSheet();
        return timelineView;
    }
}