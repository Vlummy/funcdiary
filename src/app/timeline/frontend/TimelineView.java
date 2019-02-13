package app.timeline.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;

public class TimelineView extends BorderPane {

    // Fields for this class
    private FrontCardView frontCardView;
    private BackCardView backCardView;
    private NavigatorControllerInterface timelineController;
    // Perifer panes
    private VBox leftPane = new VBox();
    private VBox rightPane = new VBox();
    private HBox topPane = new HBox();
    private HBox bottomPane = new HBox();
    // Center pane
    private StackPane stackPane;
    // app.Main Layout

    public TimelineView(FrontCardView frontCardView, BackCardView backCardView) {
        // Set dependencies
        setFrontCardView(frontCardView);
        setBackCardView(backCardView);
        getFrontCardView().getView().setOnMouseClicked(event -> getTimelineController().stepIn(frontCardView));
        getBackCardView().getView().setOnMouseClicked(event -> getTimelineController().stepOut(frontCardView));
        // Set the borderPane and stackPane. Add stackPane to CENTER
        setStackPane(new StackPane());
        getStackPane().setId("timelineCardContainer");
        getStackPane().setMaxSize(1200, 600);
        getStackPane().setMinSize(600, 300);
        getStackPane().setPrefSize(1000, 500);
        // Set default style
        super.setStyle("-fx-background-color: white");
        getStackPane().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        // Set Grow ALWAYS to perifer panes
        VBox.setVgrow(getLeftPane(), Priority.ALWAYS);
        VBox.setVgrow(getRightPane(), Priority.ALWAYS);
        HBox.setHgrow(getTopPane(), Priority.ALWAYS);
        HBox.setHgrow(getBottomPane(), Priority.ALWAYS);
        // Set size on perifer panes
        getLeftPane().setPrefWidth(100);
        getRightPane().setPrefWidth(100);
        getBottomPane().setPrefHeight(50);
        getTopPane().setPrefHeight(50);
        // Distribute layout components
        super.setTop(getTopPane());
        super.setCenter(getStackPane());
        super.setBottom(getBottomPane());
        super.setLeft(getLeftPane());
        super.setRight(getRightPane());
        // Add BackCardView layout to stackPane, then add FrontCardView to stackPane on top
        getStackPane().getChildren().addAll(getBackCardView().getView(), getFrontCardView().getView());
        // Left and right panes construction
        Label iconRight = new Label();
        iconRight.setGraphic(new ImageView(new Image("app/timeline/frontend/icons/arrow-right.png")));
        getRightPane().getChildren().add(iconRight);
        getRightPane().setAlignment(Pos.CENTER);
        Label iconLeft = new Label();
        iconLeft.setGraphic(new ImageView(new Image("app/timeline/frontend/icons/arrow-left.png")));
        getLeftPane().getChildren().add(iconLeft);
        getLeftPane().setAlignment(Pos.CENTER);
        // Add ActionEvent listener
        iconLeft.setOnMouseClicked(event -> getTimelineController().previous(getFrontCardView(), getBackCardView()));
        iconRight.setOnMouseClicked(event -> getTimelineController().next(getFrontCardView(), getBackCardView()));
        getFrontCardView().getView().setOnScroll((ScrollEvent event) -> {
            if(event.getDeltaY() > 0) {
                getTimelineController().previous(getFrontCardView(), getBackCardView());
            } else if(event.getDeltaY() < 0) {
                getTimelineController().next(getFrontCardView(), getBackCardView());
            }
        });
        setTimelineController(new NavigatorController(getFrontCardView(), getBackCardView(), getStackPane()));
    }

    public FrontCardView getFrontCardView() {
        return frontCardView;
    }

    public void setFrontCardView(FrontCardView frontCardView) {
        this.frontCardView = frontCardView;
    }

    public BackCardView getBackCardView() {
        return backCardView;
    }

    public void setBackCardView(BackCardView backCardView) {
        this.backCardView = backCardView;
    }

    public NavigatorControllerInterface getTimelineController() {
        return timelineController;
    }

    public void setTimelineController(NavigatorControllerInterface timelineController) {
        this.timelineController = timelineController;
    }

    public BorderPane getView() {

        return this;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public VBox getLeftPane() {
        return leftPane;
    }

    public void setLeftPane(VBox leftPane) {
        this.leftPane = leftPane;
    }

    public VBox getRightPane() {
        return rightPane;
    }

    public void setRightPane(VBox rightPane) {
        this.rightPane = rightPane;
    }

    public HBox getTopPane() {
        return topPane;
    }

    public void setTopPane(HBox topPane) {
        this.topPane = topPane;
    }

    public HBox getBottomPane() {
        return bottomPane;
    }

    public void setBottomPane(HBox bottomPane) {
        this.bottomPane = bottomPane;
    }

    public void removeStyleSheet() {
        getView().setStyle(null);
        getFrontCardView().getView().setStyle(null);
        getBackCardView().getView().setStyle(null);
        getStackPane().setStyle(null);
        getLeftPane().setStyle(null);
        getRightPane().setStyle(null);
        getBottomPane().setStyle(null);
        getTopPane().setStyle(null);
    }
}
