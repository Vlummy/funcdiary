package app.client;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class Draggable {
    double xOffset = 0;
    double yOffset = 0;

    public Draggable() {
    }

    public void makeDraggable(Stage primaryStage, Parent root) {
        root.getChildrenUnmodifiable().get(1).setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.getChildrenUnmodifiable().get(1).setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
}