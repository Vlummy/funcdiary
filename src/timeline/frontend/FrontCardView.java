package timeline.frontend;

import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
     * Constructs a cards
     */
    public FrontCardView() {
        // Set grow
        VBox.setVgrow(getView(), Priority.ALWAYS);

        // Set default style
        getView().setStyle("-fx-background-color: white; -fx-padding: 5 30 5 30; -fx-background-radius: 8; -fx-border-radius: 8;");
        getView().setMaxSize(1200, 600);
        getView().setMinSize(600, 300);
        getView().setPrefSize(1000, 500);
        getView().setSpacing(10);
        getH1().setStyle("-fx-font-size: 32");
        getH2().setStyle("-fx-font-size: 24; -fx-padding: 0 50 0 50;");
        getH3().setStyle("-fx-font-size: 18; -fx-padding: 0 50 0 50;");
        getH4().setStyle("-fx-font-size: 16; -fx-padding: 0 50 0 50;");
        getH5().setStyle("-fx-font-size: 13; -fx-padding: 0 50 0 50;");
        getH6().setStyle("-fx-font-size: 10; -fx-padding: 0 50 0 50;");
        getBody().setStyle("-fx-padding: 0 50 0 50;");

        // Set style id's
        getView().setId("FrontCardView");
        getH1().setId("FrontCardH1");
        getH2().setId("FrontCardH2");
        getH3().setId("FrontCardH3");
        getH4().setId("FrontCardH4");
        getH5().setId("FrontCardH5");
        getH6().setId("FrontCardH6");
        getBody().setId("FrontCardBody");
        getDate().setId("FrontCardDate");

        // Set wrap text on labels
        getH1().setWrapText(true);
        getH2().setWrapText(true);
        getH3().setWrapText(true);
        getH4().setWrapText(true);
        getH5().setWrapText(true);
        getH6().setWrapText(true);
        getBody().setWrapText(true);

        // Top spacer
        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);
        HBox top = new HBox(getH1(), topSpacer, getDate());

        getView().getChildren().addAll(top, getH2(), getH3(), getH4(), getH5(), getH6(), getBody());
    }

    public void removeUnusedLabels() {
        if(getH1().getText().equals("")) {
            getH1().setManaged(false);
        }
        if(getH2().getText().equals("")) {
            getH2().setManaged(false);
        }
        if(getH3().getText().equals("")) {
            getH3().setManaged(false);
        }
        if(getH4().getText().equals("")) {
            getH4().setManaged(false);
        }
        if(getH5().getText().equals("")) {
            getH5().setManaged(false);
        }
        if(getH6().getText().equals("")) {
            getH6().setManaged(false);
        }
        if(getBody().getText().equals("")) {
            getBody().setManaged(false);
        }
    }

    public void createTags(ArrayList<String> tags) {
        for (String tag : tags) {
            Label tg = new Label(tag);
            tg.setWrapText(true);
            tg.getStyleClass().add("Tag");
            getView().getChildren().add(tg);
        }
    }

    public VBox getView() {
        return this.view;
    }

    public Label getDate() {
        return date;
    }

    private void setDate(Label date) {
        this.date = date;
    }

    public Label getH1() {
        return h1;
    }

    private void setH1(Label h1) {
        this.h1 = h1;
    }

    public Label getH2() {
        return h2;
    }

    private void setH2(Label h2) {
        this.h2 = h2;
    }

    public Label getH3() {
        return h3;
    }

    private void setH3(Label h3) {
        this.h3 = h3;
    }

    public Label getH4() {
        return h4;
    }

    private void setH4(Label h4) {
        this.h4 = h4;
    }

    public Label getH5() {
        return h5;
    }

    private void setH5(Label h5) {
        this.h5 = h5;
    }

    public Label getH6() {
        return h6;
    }

    private void setH6(Label h6) {
        this.h6 = h6;
    }

    public Label getBody() {
        return body;
    }

    private void setBody(Label body) {
        this.body = body;
    }
}
