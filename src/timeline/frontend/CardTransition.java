package timeline.frontend;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class CardTransition {
    private TranslateTransition swipeLeft = new TranslateTransition();
    private TranslateTransition swipeRight = new TranslateTransition();
    private TranslateTransition resetTransition = new TranslateTransition();
    private FadeTransition fade = new FadeTransition();

    public CardTransition() {
        getSwipeLeft().setDuration(Duration.millis(200));
        getSwipeRight().setDuration(Duration.millis(200));
    }


    public TranslateTransition getSwipeLeft() {
        return swipeLeft;
    }

    public void swipeLeft(Node node) {
        getSwipeLeft().setNode(node);
        getSwipeLeft().setFromX(node.getTranslateX());
        getSwipeLeft().setToX(-1200);
    }

    public TranslateTransition getSwipeRight() {
        return swipeRight;
    }

    public void swipeRight(Node node) {
        getSwipeRight().setNode(node);
        getSwipeRight().setFromX(node.getTranslateX());
        getSwipeRight().setToX(1200);
    }

    public void resetPosition(Node node) {
        getSwipeLeft().setOnFinished(event -> {});
        getSwipeRight().setOnFinished(event -> {});
        getResetTransition().setNode(node);
        getResetTransition().setDuration(Duration.millis(200));
        if(node.getTranslateX() == -1200.0) {
            getResetTransition().setFromX(1200);
            getResetTransition().setToX(0);
        }
        if(node.getTranslateX() == 1200.0) {
            getResetTransition().setFromX(-1200);
            getResetTransition().setToX(0);
        }
        getResetTransition().play();
    }


    public TranslateTransition getResetTransition() {
        return resetTransition;
    }

    public void setResetTransition(TranslateTransition resetTransition) {
        this.resetTransition = resetTransition;
    }

    public void fade(Node node) {
        getFade().setNode(node);
        getFade().setDuration(Duration.millis(200));
        if(node.getOpacity() == 0) {
            getFade().setFromValue(0);
            getFade().setToValue(1);
        }
        if(node.getOpacity() == 1) {
            getFade().setFromValue(1);
            getFade().setToValue(0);
        }
        getFade().play();
    }

    public FadeTransition getFade() {
        return fade;
    }

    public void setFade(FadeTransition fade) {
        this.fade = fade;
    }
}
