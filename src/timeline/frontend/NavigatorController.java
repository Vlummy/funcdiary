package timeline.frontend;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import server.Day;
import server.DaysCollection;
import server.SaveLoadObjectsToFile;
import timeline.backend.Card;
import timeline.backend.DayLoader;
import timeline.backend.LoaderInterface;
import timeline.backend.Timeline;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * NavigatorController controls the timeline
 * VIKTIG! DENNE MÅ REFAKTORERES OG GJØRES MED GENERELL
 *
 */
public class NavigatorController implements NavigatorControllerInterface {
    private CardTransition cardTransition;
    private Timeline timeline;
    private StackPane stackPane;

    public NavigatorController(FrontCardView frontCardView, BackCardView backCardView, StackPane stackPane) {
        setStackPane(stackPane);
        setCardTransition(new CardTransition());
        getCardTransition().swipeLeft(stackPane);
        getCardTransition().swipeRight(stackPane);
        // Create card and loader for Timeline
        // Load the daysCollection hashmap
        initialise(frontCardView, backCardView, stackPane);
        newest(frontCardView, backCardView);
    }

    @Override
    public void initialise(FrontCardView frontCardView, BackCardView backCardView, StackPane stackPane) {
        setStackPane(stackPane);
        setCardTransition(new CardTransition());
        getCardTransition().swipeLeft(stackPane);
        getCardTransition().swipeRight(stackPane);
        HashMap<String, Day> collection = null;
        try {
            collection = ((DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser")).getDayCollection();
            LoaderInterface<Day, LocalDate> loader = new DayLoader(collection);
            setTimeline(new Timeline(new Card(), loader));
        } catch (Exception e) {

        }
    }

    @Override
    public void next(FrontCardView frontCardView, BackCardView backCardView) {
        if(getTimeline().getLoader().hasNext()) {
            getCardTransition().getSwipeLeft().play();
            getCardTransition().getSwipeLeft().setOnFinished(event -> {
                getTimeline().getLoader().loadNextEntry();
                createCards(frontCardView, backCardView);
                getCardTransition().resetPosition(getStackPane());
            });
        }
    }

    @Override
    public void previous(FrontCardView frontCardView, BackCardView backCardView) {
        if(getTimeline().getLoader().hasPrevious()) {
            getCardTransition().getSwipeRight().play();
            getCardTransition().getSwipeRight().setOnFinished(event -> {
                getTimeline().getLoader().loadPreviousEntry();
                createCards(frontCardView, backCardView);
                getCardTransition().resetPosition(getStackPane());

            });
        }
    }

    @Override
    public void specific(LocalDate date, FrontCardView frontCardView, BackCardView backCardView) {
        getTimeline().getLoader().loadSpecificEntry(date);
        createCards(frontCardView, backCardView);
    }

    private void createCards(FrontCardView frontCardView, BackCardView backCardView) {
        Day day = (Day) getTimeline().getLoader().getEntry();
        frontCardView.getDate().setText(day.getDate().toString());
        frontCardView.getH1().setText(day.getTitle());
        frontCardView.getH2().setText(day.getCrucialExperience());
        frontCardView.getH3().setText(day.getPersonalExperience());
        frontCardView.getH4().setText(day.getKnowledgeObtained());
        frontCardView.getH5().setText("Rate: " + day.getRating());
        frontCardView.getBody().setText(day.getRecapStory());
        frontCardView.removeUnusedLabels();
        try {
            backCardView.setImage1(new Image(day.getImageOne()));
            backCardView.setImage2(new Image(day.getImageTwo()));
            backCardView.setImageRotation1(day.getImageOneRotation());
            backCardView.setImageRotation2(day.getImageTwoRotation());
        } catch (Exception e) {
            System.out.println("There are noe images");
        }
    }

    @Override
    public void newest(FrontCardView frontCardView, BackCardView backCardView) {
        try {
            getTimeline().getLoader().loadNewestEntry();
            createCards(frontCardView, backCardView);
        } catch (Exception e) {

        }
    }

    @Override
    public void stepIn(FrontCardView frontCardView) {
        getCardTransition().fade(frontCardView.getView());
    }

    @Override
    public void stepOut(FrontCardView frontCardView) {
        getCardTransition().fade(frontCardView.getView());
    }

    private CardTransition getCardTransition() {
        return cardTransition;
    }

    private void setCardTransition(CardTransition cardTransition) {
        this.cardTransition = cardTransition;
    }

    private Timeline getTimeline() {
        return timeline;
    }

    private void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }
}
