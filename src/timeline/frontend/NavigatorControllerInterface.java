package timeline.frontend;

import javafx.scene.layout.StackPane;

import java.time.LocalDate;

/**
 * NavigatorControllerInterface is the method signatures that is used for controlling a
 * CardNavigator.
 *
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public interface NavigatorControllerInterface {
    /**
     * This method initialises the backend of the navigator
     * Usually the domain spesific class has a Navigator, StackPane and Cardtransition reference which is set
     * in this class. Use this method in the constructor of the NavigatorController.
     * After, use newest() to load in the newest entry.
     */
    void initialise(FrontCardView frontCardView, BackCardView backCardView, StackPane stackPane);
    /**
     * Use this method to select next card
     * @param frontCardView
     * @param backCardView
     */
    void next(FrontCardView frontCardView, BackCardView backCardView);

    /**
     * Use this method to select previous card
     * @param frontCardView
     * @param backCardView
     */
    void previous(FrontCardView frontCardView, BackCardView backCardView);

    /**
     * Loads specific date card
     * @param frontCardView
     * @param backCardView
     */
    void specific(LocalDate identifier, FrontCardView frontCardView, BackCardView backCardView);

    /**
     * This method is used to step in to the front card and view the back card
     * @param frontCardView
     */
    void stepIn(FrontCardView frontCardView);

    /**
     * This method is used to step out of the back card and view the front card
     * @param frontCardView
     */
    void stepOut(FrontCardView frontCardView);

    /**
     * This method is used to load the newest card
     * @param frontCardView
     * @param backCardView
     */
    void newest(FrontCardView frontCardView, BackCardView backCardView);
}
