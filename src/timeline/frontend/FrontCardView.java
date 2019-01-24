package timeline.frontend;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * FrontCardView is the front card of the navigator. It contains default headers from h1 to h6, a body label
 * for representing text, a date label for representing date.
 * This class contains a VBox that represents the actual view/layout of the card. To get the view
 * use the method getView().
 *
 * ID's for this class:
 * #frontCardH1
 * #frontCardH2
 * #frontCardH3
 * #frontCardH4
 * #frontCardH5
 * #frontCardH6
 * #frontCardBody
 * #frontCardDate
 * #frontCardView
 *
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public class FrontCardView {
    // Fields for this class
    private VBox view = new VBox();
    private Label date = new Label();
    private Label h1 = new Label();
    private Label h2 = new Label();
    private Label h3 = new Label();
    private Label h4 = new Label();
    private Label h5 = new Label();
    private Label h6 = new Label();
    private Label body = new Label();

    /**
     * Constructs a card with default properties
     */
    public FrontCardView() {
        // Set default style
        getView().setStyle("-fx-background-color: white; -fx-padding: 5 30 5 30; -fx-background-radius: 8; -fx-border-radius: 8;");
        getView().setMaxSize(1200, 600);
        getView().setMinSize(600, 300);
        getView().setPrefSize(1000, 500);
        getView().setSpacing(5);
        getH1().setStyle("-fx-font-size: 32");
        getH2().setStyle("-fx-font-size: 24;");
        getH3().setStyle("-fx-font-size: 18;");
        getH4().setStyle("-fx-font-size: 16;");
        getH5().setStyle("-fx-font-size: 13;");
        getH6().setStyle("-fx-font-size: 10;");

        // Set style id's
        getView().setId("frontCardView");
        getH1().setId("frontCardH1");
        getH2().setId("frontCardH2");
        getH3().setId("frontCardH3");
        getH4().setId("frontCardH4");
        getH5().setId("frontCardH5");
        getH6().setId("frontCardH6");
        getBody().setId("frontCardBody");
        getDate().setId("frontCardDate");

        // Set wrap text on labels
        getH1().setWrapText(true);
        getH2().setWrapText(true);
        getH3().setWrapText(true);
        getH4().setWrapText(true);
        getH5().setWrapText(true);
        getH6().setWrapText(true);

        // Top spacer
        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);
        HBox top = new HBox(getH1(), topSpacer, getDate());

        getView().getChildren().addAll(top, getH2(), getH3(), getH4(), getH5(), getH6(), addScrollPane(getBody()));
    }

    /**
     * Add scrollpane to body
     */
    private ScrollPane addScrollPane(Label body) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(body);
        body.setWrapText(true);
        scrollPane.setStyle("-fx-border-color: white; -fx-border-width: 0.2 0 0 0;");
        scrollPane.setFitToWidth(true);
        scrollPane.getStylesheets().add("timeline/frontend/stylesheets/scrollpane.css");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return scrollPane;
    }

    /**
     * Removes unused labels so they don't take up space
     */
    public void removeUnusedLabels() {
        if(getH1().getText().equals("")) {
            getH1().setManaged(false);
        } else {
            getH1().setManaged(true);
        }
        if(getH2().getText().equals("")) {
            getH2().setManaged(false);
        } else {
            getH2().setManaged(true);
        }
        if(getH3().getText().equals("")) {
            getH3().setManaged(false);
        } else {
            getH3().setManaged(true);
        }
        if(getH4().getText().equals("")) {
            getH4().setManaged(false);
        } else {
            getH4().setManaged(true);
        }
        if(getH5().getText().equals("")) {
            getH5().setManaged(false);
        } else {
            getH5().setManaged(true);
        }
        if(getH6().getText().equals("")) {
            getH6().setManaged(false);
        } else {
            getH6().setManaged(true);
        }
        if(getBody().getText().equals("")) {
            getBody().setManaged(false);
        } else {
            getBody().setManaged(true);
        }
    }

    /**
     * This method is used for creating tags from a list containing strings (tags)
     * @param tags
     */
    public ArrayList<Label> createTags(ArrayList<String> tags) {
        ArrayList<Label> tagLabels = new ArrayList<>();
        for (String tag : tags) {
            Label tg = new Label(tag);
            tg.setWrapText(true);
            tg.getStyleClass().add("Tag");
            tagLabels.add(tg);
        }
        return tagLabels;
    }

    /**
     * Returns the actual view of the front card
     * @return view
     */
    public VBox getView() {
        return this.view;
    }

    /**
     * Get the date label
     * @return date
     */
    public Label getDate() {
        return date;
    }

    /**
     * Set the date label
     * @param date
     */
    private void setDate(Label date) {
        this.date = date;
    }

    /**
     * Get the header 1 label
     * @return h1
     */
    public Label getH1() {
        return h1;
    }

    /**
     * Set the header 1 label
     * @param h1
     */
    private void setH1(Label h1) {
        this.h1 = h1;
    }

    /**
     * Get the header 2 label
     * @return h2
     */
    public Label getH2() {
        return h2;
    }

    /**
     * Set the header 2 label
     * @param h2
     */
    private void setH2(Label h2) {
        this.h2 = h2;
    }

    /**
     * Get the header 3 label
     * @return h3
     */
    public Label getH3() {
        return h3;
    }

    /**
     * Set the header 3 label
     * @param h3
     */
    private void setH3(Label h3) {
        this.h3 = h3;
    }

    /**
     * Get the header 4 label
     * @return
     */
    public Label getH4() {
        return h4;
    }

    /**
     * Set the header 4 label
     * @param h4
     */
    private void setH4(Label h4) {
        this.h4 = h4;
    }

    /**
     * Get the header 5 label
     * @return h5
     */
    public Label getH5() {
        return h5;
    }

    /**
     * Set the header 5 label
     * @param h5
     */
    private void setH5(Label h5) {
        this.h5 = h5;
    }

    /**
     * Get the header 6 label
     * @return h6
     */
    public Label getH6() {
        return h6;
    }

    /**
     * Set the header 6 label
     * @param h6
     */
    private void setH6(Label h6) {
        this.h6 = h6;
    }

    /**
     * Get the body text
     * @return
     */
    public Label getBody() {
        return body;
    }

    /**
     * Set the body text
     * @param body
     */
    private void setBody(Label body) {
        this.body = body;
    }
}
