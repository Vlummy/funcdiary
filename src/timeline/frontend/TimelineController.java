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
 * TimelineController controls the timeline
 * VIKTIG! DENNE MÅ REFAKTORERES OG GJØRES MED GENERELL
 *
 */
public class TimelineController implements ControllerInterface {
    private CardTransition cardTransition;
    private Timeline timeline;
    private StackPane stackPane;

    public TimelineController(FrontCardView frontCardView, BackCardView backCardView, StackPane stackPane) {
        setStackPane(stackPane);
        setCardTransition(new CardTransition());
        getCardTransition().swipeLeft(stackPane);
        getCardTransition().swipeRight(stackPane);
        // Create card and loader for Timeline
        // Load the daysCollection hashmap
        try {
            HashMap<String, Day> collection = ((DaysCollection)SaveLoadObjectsToFile.loadObject("daysCollection.ser")).getDayCollection();
            LoaderInterface<Day, LocalDate> loader = new DayLoader(collection);
            setTimeline(new Timeline(new Card(), loader));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Construct a card
        getTimeline().getLoader().loadNewestEntry();
        Card card = getTimeline().getCard();
        Day day = (Day) getTimeline().getLoader().getEntry();
        try {
            card.setH1(day.getTitle());
            card.setH2(day.getCrucialExperience());
            card.setH3(day.getPersonalExperience());
            card.setH4(day.getKnowledgeObtained());
            card.setBody(day.getRecapStory());
            card.setDate(day.getDate().toString());
            card.addImagePath(day.getImageOne());
            card.addImagePath(day.getImageTwo());
            card.setTags(day.getTags());
            // Set frontEndCard and backEndCard
            frontCardView.getDate().setText(card.getDate());
            frontCardView.getH1().setText(card.getH1());
            frontCardView.getH2().setText(card.getH2());
            frontCardView.getH3().setText(card.getH3());
            frontCardView.getH4().setText(card.getH4());
            frontCardView.getBody().setText(card.getBody());
            backCardView.setImage1(new Image(card.getImagePaths().get(0)));
            backCardView.setImage2(new Image(card.getImagePaths().get(1)));
            backCardView.setImageRotation1(day.getImageOneRotation());
            backCardView.setImageRotation2(day.getImageTwoRotation());
        } catch (Exception e) {
            e.printStackTrace();
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

    private void createCards(FrontCardView frontCardView, BackCardView backCardView) {
        Day day = (Day) getTimeline().getLoader().getEntry();
        frontCardView.getDate().setText(day.getDate().toString());
        frontCardView.getH1().setText(day.getTitle());
        frontCardView.getH2().setText(day.getCrucialExperience());
        frontCardView.getH3().setText(day.getPersonalExperience());
        frontCardView.getH4().setText(day.getKnowledgeObtained());
        frontCardView.getBody().setText(day.getRecapStory());
        backCardView.setImage1(new Image(day.getImageOne()));
        backCardView.setImage2(new Image(day.getImageTwo()));
        backCardView.setImageRotation1(day.getImageOneRotation());
        backCardView.setImageRotation2(day.getImageTwoRotation());
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
