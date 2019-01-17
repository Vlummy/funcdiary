package timeline;

/**
 * This class is a client Interface for the Timeline.
 * It has the responsibility of managing the components of a Timeline. this can the Loader and the Cards.
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public class Timeline {
    // Fields for this class
    private Card card;
    private LoaderInterface loader;

    /**
     * Constructor: Empty
     */
    public Timeline() {

    }

    /**
     * Constructor: Initialises a new Timeline with dependent components
     * @param card
     * @param loader
     */
    public Timeline(Card card, LoaderInterface loader) {
        setCard(card);
        setLoader(loader);
    }

    /**
     * Get card
     * @return card
     */
    public Card getCard() {
        return card;
    }

    /**
     * Set card
     * @param card
     */
    private void setCard(Card card) {
        this.card = card;
    }

    /**
     * Get loader
     * @return loader
     */
    public LoaderInterface getLoader() {
        return loader;
    }

    /**
     * Set loader
     * @param loader
     */
    public void setLoader(LoaderInterface loader) {
        this.loader = loader;
    }
}
