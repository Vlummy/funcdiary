package client;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Class for viewing a cloud og tags
 * To exit the TagCloudViewer, click one of the tags
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public class TagCloudViewer {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     *
     * @param node - This node has to be a node from the parent/root so that the TagcloudVewier can get hold of the
     *              layout and blur it when viewing the TagCloudViewer ( Usually the button clicked for viewing
     *              the TagCloudViewer  )
     * @param tagsForTagCloudViewer - The list of tags then is going to be viewed. This is a ArrayList of labels
     */
    public TagCloudViewer(Node node, ArrayList<Label> tagsForTagCloudViewer) {
        Stage stage = createStage();
        GridPane gridPane = createGridPane();
        Parent parent = blurParent(node);
        Scene scene = createScene(stage, gridPane, parent);
        TranslateTransition translateTransition1 = getTransitionMotionForTags(gridPane);
        distributeTags(stage, gridPane, scene, translateTransition1, tagsForTagCloudViewer);
        stage.setMinWidth(screenSize.getWidth());
        stage.setMinHeight(screenSize.getHeight());

    }

    /**
     * Distributes the tag labels on a gridpane, plays the transition and show the stage
     * @param stage - A stage where the layout should be
     * @param gridPane - The layout for the scene
     * @param scene - The scene of this stage
     * @param translateTransition1 - The animations to be played on the layout
     * @param tagLabels - A list of tags to be viewed
     */
    private void distributeTags(Stage stage, GridPane gridPane, Scene scene, TranslateTransition translateTransition1, ArrayList<Label> tagLabels) {
        try {
            int col = 1;
            int row = 1;
            for (Label label : tagLabels) {
                label.setStyle("-fx-border-radius: 12; -fx-background-radius: 12; -fx-font-size: 48; -fx-background-color: #536a74; -fx-text-fill: #d0d6d9; -fx-text-alignment: center; -fx-padding: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 50, 0, 0, 0);");
                gridPane.add(label, col, row);
                col++;
                if (col == (row + 2)) {
                    row++;
                    col = 1;
                }
            }
            translateTransition1.play();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A contrustructed translate animation
     * @param gridPane - The layout in which the animation should control
     * @return translateTransition2 - the animation to be played
     */
    private TranslateTransition getTransitionMotionForTags(GridPane gridPane) {
        TranslateTransition translateTransition1 = new TranslateTransition();
        translateTransition1.setNode(gridPane);
        translateTransition1.setFromY(1000);
        translateTransition1.setToY(0);
        translateTransition1.setDuration(Duration.millis(200));

        TranslateTransition translateTransition2 = new TranslateTransition();
        translateTransition2.setNode(gridPane);
        translateTransition2.setFromY(-50);
        translateTransition2.setToY(0);
        translateTransition2.setAutoReverse(true);
        translateTransition2.setDuration(Duration.millis(300));

        translateTransition1.setOnFinished(event1 -> {
            translateTransition2.play();
        });
        return translateTransition1;
    }

    /**
     * Create a stage for this TagCloudViewer
     * @return stage - return a new transparent Stage
     */
    private Stage createStage() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        return stage;
    }

    /**
     * Create GridPane for this TagCloudViewer
     * @return gridpane layout
     */
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: rgba(0,0,0,0);");
        gridPane.setHgap(100);
        gridPane.setVgap(80);
        return gridPane;
    }

    /**
     * Create a scene for this TagCloudViewer
     * @param stage
     * @param gridPane
     * @param parent
     * @return
     */
    private Scene createScene(Stage stage, GridPane gridPane, Parent parent) {

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(gridPane);
        translateTransition.setFromY(0);
        translateTransition.setToY(1000);
        translateTransition.setDuration(Duration.millis(200));
        translateTransition.setOnFinished(event -> {
            parent.setEffect(null);
            stage.close();
        });

        Scene scene = new Scene(gridPane);
        scene.setFill(Color.TRANSPARENT);
        scene.setOnMouseClicked(event1 -> {
            translateTransition.play();
        });
        return scene;
    }

    /**
     * Blur's the parent scene of the node that is paramater. Usually this is the button clicked to open the TagCloudViewer
     * @param node - Usually the button clicked to view the tagCloudViewer
     * @return parent
     */
    private Parent blurParent(Node node) {
        Parent parent = node.getScene().getRoot();
        parent.setEffect(new GaussianBlur(20));
        return parent;
    }
}