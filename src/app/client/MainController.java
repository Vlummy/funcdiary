package app.client;
import com.jfoenix.controls.JFXButton;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import app.server.*;
import app.timeline.frontend.TimelineView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static app.server.SaveLoadObjectsToFile.loadObject;
import static app.server.SaveLoadObjectsToFile.saveObject;

/**
 * Class: MainController
 * Purpose: Controller for the Diary scene
 */
public class MainController implements Controller {
    @FXML private DatePicker dPicker;
    @FXML private TextField dCrucialExperience;
    @FXML private TextField dTitle;
    @FXML private TextField dPersonalExperience;
    @FXML private TextField dKnowledgeObtained;
    @FXML private TextArea dStoryOfTheDay;
    @FXML private Button rb5;
    @FXML private Button rb4;
    @FXML private Button rb3;
    @FXML private Button rb2;
    @FXML private Button rb1;
    @FXML private Button saveButton;
    @FXML private TextField tagTextField;
    @FXML private Label tagWordCountLabel;
    @FXML private Label enoughTagsLabel;
    @FXML private ImageView imageViewOne;
    @FXML private ImageView imageViewTwo;
    @FXML private StackPane imageContainerOne;
    @FXML private StackPane imageContainerTwo;
    @FXML private TimelineView timelineView;
    private String imageOnePath;
    private String imageTwoPath;
    private int rating = 3;

    @FXML private VBox tagsCollectionPane;
    private ArrayList<String> tagsList;
    private ArrayList<String> loadedTagsList;

    private Button rotateImageOne;
    private Button rotateImageTwo;

    /**
     * Initialize the scene
     */
    public void initialize() {
        if(this.tagsList == null) {
            this.tagsList = new ArrayList<>();
        }
        tagTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                addTagToCurrentDay(tagsCollectionPane, tagTextField.getText());
                tagTextField.clear();
            }
        });
        setTheRotateButtons();

        setRotateButtons(rotateImageOne, imageViewOne, imageContainerOne);
        setRotateButtons(rotateImageTwo, imageViewTwo, imageContainerTwo);

        rb1.setTooltip(new Tooltip("Rate your day 1"));
        rb2.setTooltip(new Tooltip("Rate your day 2"));
        rb3.setTooltip(new Tooltip("Rate your day 3"));
        rb4.setTooltip(new Tooltip("Rate your day 4"));
        rb5.setTooltip(new Tooltip("Rate your day 5"));

        saveButton.setTooltip(new Tooltip("Saves your day to the selected date"));
        tagTextField.setTooltip(new Tooltip("Write your tag and press Enter"));

        // Make tag cloud on button click
        JFXButton tagCloudButton = new JFXButton("View Tags");
        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);
        timelineView.getTopPane().setStyle("-fx-padding: 10");
        timelineView.getBottomPane().setStyle("-fx-padding: 10");
        timelineView.getTopPane().getChildren().addAll(spacerTop, tagCloudButton);
        tagCloudButton.setOnAction(event -> {
            new TagCloudViewer(tagCloudButton, getTagsForTagCloudViewer());
        });
    }

    public void setTimelineViewRate(Label rateLabel) {
        try {
            DaysCollection daysCollection = (DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser");
            HashMap<String, Day> map = daysCollection.getDayCollection();
            String rate = map.get(timelineView.getFrontCardView().getDate().getText()).getRating() + "";
            System.out.println(rate);
            rateLabel.setText(rate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get tags from the current loaded day in the CardNavigator
     * @return Label -- a list of tags
     */
    public ArrayList<Label> getTagsForTagCloudViewer() {
        DaysCollection daysCollection = null;
        try {
            daysCollection = (DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Day> days = daysCollection.getDayCollection();
        Day day = days.get(timelineView.getFrontCardView().getDate().getText());
        return timelineView.getFrontCardView().createTags(day.getTags());
    }

    /**
     * Method: Sets the rotate function and scales the selected image view to fit size
     * @param rotateButton
     * @param imageView
     * @param imageContainer
     */
    private void setRotateButtons(Button rotateButton, ImageView imageView, StackPane imageContainer) {
        rotateButton.setOnAction(event -> {
            rotateImage90(imageView, imageView.getRotate() + 90);
            scaleImageToFit(imageView, imageContainer);
        });
    }

    private void rotateImage90(ImageView imageView, double v) {
        imageView.setRotate(v);
    }

    private void scaleImageToFit(ImageView imageView, StackPane imageContainer) {
        if(imageView.getRotate() == 90 || imageView.getRotate() == 270) {
            imageView.setFitWidth(imageContainer.getHeight());
            imageView.setFitHeight(imageContainer.getWidth());
        } else {
            imageView.setFitWidth(450);
            imageView.setFitHeight(250);
        }
        if(imageView.getRotate() == 360) {
            rotateImage90(imageView, 0);
        }
    }

    private void setTheRotateButtons() {
        rotateImageOne = new Button();
        rotateImageOne.setGraphic(new ImageView(new Image("app/resources/rotate-icon.png")));
        rotateImageOne.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        rotateImageTwo = new Button();
        rotateImageTwo.setGraphic(new ImageView(new Image("app/resources/rotate-icon.png")));
        rotateImageTwo.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");

        imageContainerOne.getChildren().add(rotateImageOne);
        imageContainerTwo.getChildren().add(rotateImageTwo);
        imageContainerOne.setAlignment(rotateImageOne, Pos.TOP_RIGHT);
        imageContainerTwo.setAlignment(rotateImageTwo, Pos.TOP_RIGHT);
    }

    private String getImageOne() {
        String path = "";
        if(imageOnePath != null) {
            path = imageOnePath;
        }
        return path;
    }

    private String getImageTwo() {
        String path = "";
        if(imageTwoPath != null) {
            path = imageTwoPath;
        }
        return path;
    }

    private void setImageViewOne(Image image) {
        this.imageViewOne.setImage(image);
    }

    private void setImageViewTwo(Image image) {
        this.imageViewTwo.setImage(image);
    }

    public void selectImageOne() {
        ImageFileChooser imageFileChooser = new ImageFileChooser(new FileChooser(), new Stage());
        //String path = "img/" + imageFileChooser.getFile().getName();
        Image image = new Image(imageFileChooser.getFile().toURI().toString());
        setImageViewOne(image);
        this.imageOnePath = imageFileChooser.getFile().toURI().toString();

    }

    public void selectImageTwo() {
        ImageFileChooser imageFileChooser = new ImageFileChooser(new FileChooser(), new Stage());
        //String path = "img/" + imageFileChooser.getFile().getName();
        Image image = new Image(imageFileChooser.getFile().toURI().toString());
        setImageViewTwo(image);
        this.imageTwoPath = imageFileChooser.getFile().toURI().toString();
    }

    public void resetImageViews() {
        imageViewOne.setImage(null);
        imageViewTwo.setImage(null);
        imageViewOne.setFitHeight(250);
        imageViewTwo.setFitWidth(450);
        imageViewTwo.setFitHeight(250);
        imageViewOne.setFitWidth(450);
        imageContainerOne.setPrefHeight(250);
        imageContainerOne.setPrefWidth(450);
        imageContainerTwo.setPrefHeight(250);
        imageContainerTwo.setPrefWidth(450);
        imageOnePath = "";
        imageTwoPath = "";
    }

    public void cleanTagsList() {
        if(tagsList.size() != 0) {
            this.tagsList.clear();
        }
        if(loadedTagsList != null) {
            this.loadedTagsList.clear();
        }
        if(tagsCollectionPane.getChildren().size() != 0) {
            this.tagsCollectionPane.getChildren().clear();
        }
    }

    public void addTagToList(String tag) {
        this.tagsList.add(tag);
    }

    public void validateInputOnTextField() {
        if(tagTextField.getText().length() == 0) {
            tagWordCountLabel.setText("");
        } else {
            tagWordCountLabel.setText(String.valueOf(tagTextField.getText().length() + 1));
        }
        if(tagTextField.getText().length() > 14) {
            tagWordCountLabel.setText("15");
            String limit = tagTextField.getText().substring(0, 14);
            tagTextField.setText(limit);
            tagTextField.positionCaret(15);
        }
    }

    public void addTagToCurrentDay(VBox box,String tag) {
        if(box.getChildren().size() < 9) {
            enoughTagsLabel.setText("");
            Button deleteFunc = new Button("x");
            deleteFunc.setStyle("-fx-border-width: 0; -fx-background-color: #afc7ce;");
            Button result = new Button(tag, deleteFunc);
            result.setStyle("-fx-background-color: #afc7ce; -fx-border-color: #88979b; -fx-border-radius: 10; -fx-background-radius: 10;");
            result.setPrefHeight(20);
            result.setContentDisplay(ContentDisplay.RIGHT);

            deleteFunc.setOnAction(event -> {
                box.getChildren().remove(result);
                if(tagsList.contains(result.getText())) {
                    tagsList.remove(result.getText());
                }
                System.out.println(tagsList);
            });
            box.getChildren().add(result);
            addTagToList(tag);
            System.out.println(tagsList);
        } else {
            enoughTagsLabel.setText("Okay okay, enough!");
        }
    }

    public void burnDiary() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Burning Warning");
        alert.setHeaderText("Are you sure you want to delete the diary?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            try {
                DaysCollection collection = (DaysCollection) loadObject("daysCollection.ser");
                collection.getDayCollection().clear();
                saveObject(collection, "daysCollection.ser");
                Alert d = new Alert(Alert.AlertType.INFORMATION);
                d.setHeaderText("Everything is deleted");
                d.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(result.get() == ButtonType.CANCEL) {
            Alert nd = new Alert(Alert.AlertType.INFORMATION);
            nd.setHeaderText("Nothing is deleted");
            nd.showAndWait();
        }
    }

    /**
     * Method: minimizeWindow()
     * Purpose: Method for minimizing window because stage is transparent
     */
    public void minimizeWindow(ActionEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    /**
     * Method: exitDiary()
     * Purpose: Exit the application
     */
    public void exitDiary(ActionEvent e) {
        System.exit(000);
    }

    /**
     * Method: setRB5SelectColor()
     * Purpose: Event handler for coloring the selected rating button.
     * SelectedButton: Rating Button 5
     */
    public void setRB5SelectColor() {
        rotateAnimation(1000, this.rb5, 1800);
        scaleAnimation(500, rb4);
        scaleAnimation(500, rb3);
        scaleAnimation(500, rb2);
        scaleAnimation(500, rb1);

        this.rb1.setStyle("-fx-text-fill: white;");
        this.rb2.setStyle("-fx-text-fill: white;");
        this.rb3.setStyle("-fx-text-fill: white;");
        this.rb4.setStyle("-fx-text-fill: white;");
        this.rb5.setStyle("-fx-text-fill: grey;");
    }

    public void scaleAnimation(double duration, Node node) {
        ScaleTransition st = new ScaleTransition(Duration.millis(duration), node);
        st.setByX(-1.0f);
        st.setByY(-1.0f);
        st.setCycleCount(2);
        st.setAutoReverse(true);

        st.play();
    }

    public void rotateAnimation(double duration, Node node, double degrees) {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(duration));
        rotateTransition.setFromAngle(0.0);
        rotateTransition.setToAngle(degrees);
        rotateTransition.setNode(node);
        rotateTransition.play();
    }

    /**
     * Method: setRB4SelectColor()
     * Purpose: Event handler for coloring the selected rating button.
     * SelectedButton: Rating Button 4
     */
    public void setRB4SelectColor() {
        rotateAnimation(1000, this.rb4, 1440);
        scaleAnimation(500, rb5);
        scaleAnimation(500, rb3);
        scaleAnimation(500, rb2);
        scaleAnimation(500, rb1);
        this.rb1.setStyle("-fx-text-fill: white;");
        this.rb2.setStyle("-fx-text-fill: white;");
        this.rb3.setStyle("-fx-text-fill: white;");
        this.rb4.setStyle("-fx-text-fill: grey;");
        this.rb5.setStyle("-fx-text-fill: white;");
    }

    /**
     * Method: setRB3SelectColor()
     * Purpose: Event handler for coloring the selected rating button.
     * SelectedButton: Rating Button 3
     */
    public void setRB3SelectColor() {
        rotateAnimation(1000, this.rb3, 1080);
        scaleAnimation(500, rb5);
        scaleAnimation(500, rb4);
        scaleAnimation(500, rb2);
        scaleAnimation(500, rb1);
        this.rb1.setStyle("-fx-text-fill: white;");
        this.rb2.setStyle("-fx-text-fill: white;");
        this.rb3.setStyle("-fx-text-fill: grey;");
        this.rb4.setStyle("-fx-text-fill: white;");
        this.rb5.setStyle("-fx-text-fill: white;");
    }

    /**
     * Method: setRB2SelectColor()
     * Purpose: Event handler for coloring the selected rating button.
     * SelectedButton: Rating Button 2
     */
    public void setRB2SelectColor() {
        rotateAnimation(1000, this.rb2, 720);
        scaleAnimation(500, rb5);
        scaleAnimation(500, rb3);
        scaleAnimation(500, rb4);
        scaleAnimation(500, rb1);
        this.rb1.setStyle("-fx-text-fill: white;");
        this.rb2.setStyle("-fx-text-fill: grey;");
        this.rb3.setStyle("-fx-text-fill: white;");
        this.rb4.setStyle("-fx-text-fill: white;");
        this.rb5.setStyle("-fx-text-fill: white;");
    }

    /**
     * Method: setRB1SelectColor()
     * Purpose: Event handler for coloring the selected rating button.
     * SelectedButton: Rating Button 1
     */
    public void setRB1SelectColor() {
        rotateAnimation(1000, this.rb1, 360);
        scaleAnimation(500, rb5);
        scaleAnimation(500, rb3);
        scaleAnimation(500, rb2);
        scaleAnimation(500, rb4);
        this.rb1.setStyle("-fx-text-fill: grey;");
        this.rb2.setStyle("-fx-text-fill: white;");
        this.rb3.setStyle("-fx-text-fill: white;");
        this.rb4.setStyle("-fx-text-fill: white;");
        this.rb5.setStyle("-fx-text-fill: white;");
    }

    public void setRating(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();
        this.rating = Integer.parseInt(value);
        System.out.println(Integer.parseInt(value));
    }

    /**
     * Method: cleanDiary()
     * Purpose: Initializes the diary day view to its original state on runtime. No saved data is deleted fom the
     * saved days and collections.
     */
    public void cleanDiary() {
        cleanTagsList();
        resetRatingButtonColoring();
        resetDiaryInputFields();
        resetImageViews();
    }

    /**
     * Method: resetDiaryInputFields()
     * Purpose: Reset the input fields of the diary to original prompt text on runtime
     */
    private void resetDiaryInputFields() {
        this.dTitle.setText("");
        this.dCrucialExperience.setText("");
        this.dPersonalExperience.setText("");
        this.dKnowledgeObtained.setText("");
        this.dStoryOfTheDay.setText("");
        this.dCrucialExperience.setPromptText("Crucial Experience: What was it that made your day like this?");
        this.dPersonalExperience.setPromptText("Personal Experience: Did you experience any new feelings?");
        this.dKnowledgeObtained.setPromptText("Knowledge Obtained: What have you learned from this?");
        this.dStoryOfTheDay.setPromptText("Write the story of your day..");
        this.dTitle.setPromptText("Title");
        this.dPicker.setPromptText("Select Date");
    }

    /**
     * Method: resetRatingButtonColoring()
     * Purpose: Reset the button coloring to original state
     */
    private void resetRatingButtonColoring() {
        this.rb1.setStyle("-fx-text-fill: white;");
        this.rb2.setStyle("-fx-text-fill: white;");
        this.rb3.setStyle("-fx-text-fill: white;");
        this.rb4.setStyle("-fx-text-fill: white;");
        this.rb5.setStyle("-fx-text-fill: white;");
    }

    /**
     * Method loadDay()
     * Purpose: Loads a day by date from collection iff collection and a day exists. It then loads the variables data
     * for the day in too the matching fields of the GUI
     */
    public void loadDay() {
        if(this.tagsList.size() != 0) {
            cleanTagsList();
        }
        try {
            DaysCollection dc = (DaysCollection) loadObject("daysCollection.ser");
            if(dc.getDay(dPicker.getValue().toString()) != null) {
                Day day = dc.getDay(dPicker.getValue().toString());
                this.dTitle.setText(day.getTitle());
                this.dPicker.setPromptText(day.getDate().toString());
                this.dCrucialExperience.setText(day.getCrucialExperience());
                this.dPersonalExperience.setText(day.getPersonalExperience());
                this.dKnowledgeObtained.setText(day.getKnowledgeObtained());
                this.dStoryOfTheDay.setText(day.getRecapStory());
                this.rating = day.getRating();
                this.loadedTagsList = day.getTags();
                System.out.println(day.getImageOne());
                System.out.println(day.getImageTwo());

                try {
                    setImageViewOne(new Image(day.getImageOne()));
                    setImageViewTwo(new Image(day.getImageTwo()));
                } catch (Exception e) {

                }

                imageViewOne.setRotate(day.getImageOneRotation());
                imageViewTwo.setRotate(day.getImageTwoRotation());
                System.out.println(imageViewOne.getRotate());
                System.out.println(imageViewTwo.getRotate());
                scaleImageToFit(imageViewOne,imageContainerOne);
                scaleImageToFit(imageViewTwo,imageContainerTwo);

                this.imageOnePath = day.getImageOne();
                this.imageTwoPath = day.getImageTwo();

                for(String tag : loadedTagsList) {
                    addTagToCurrentDay(tagsCollectionPane, tag);
                }

                if(rating == 1) {
                    setRB1SelectColor();
                }
                if(rating == 2) {
                    setRB2SelectColor();
                }
                if(rating == 3) {
                    setRB3SelectColor();
                }
                if(rating == 4) {
                    setRB4SelectColor();
                }
                if(rating == 5) {
                    setRB5SelectColor();
                }
            } else {
                cleanDiary();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't find any collection of saved days, or the date is not specified");
        }
    }

    /**
     * Method: saveDay()
     * Purpose: Creates a new day and and possibly a new hashmap collection for storing days iff one not already exits.
     * The new day is saved to the collection by the date as key and day as value.
     *
     * IMPORTANT: DOES NOT HAVE PERIOD YET!
     */
    public void saveDay() {
        System.out.println(imageOnePath);
        System.out.println(imageTwoPath);
        Task<Void> task = new Task<Void>() {
            @Override public Void call() throws Exception {
                updateMessage("Saving.");
                Day day = DayFactory.createDay(DayType.SINGLE); // ONLY SINGLE AT THIS MOMENT
                DaysCollection dc;
                Thread.sleep(500);
                updateMessage("Saving..");
                try {
                    dc = (DaysCollection) loadObject("daysCollection.ser");
                } catch (Exception e) {
                    dc = new DaysCollection();
                    saveObject(dc, "daysCollection.ser");
                    dc = (DaysCollection) loadObject("daysCollection.ser");
                    System.out.println("There is no collection of days in the diary. A new one has now been created");
                }
                Thread.sleep(500);
                updateMessage("Saving...");
                day.setDate(dPicker.getValue());
                day.setTitle(dTitle.getText());
                day.setCrucialExperience(dCrucialExperience.getText());
                day.setPersonalExperience(dPersonalExperience.getText());
                day.setKnowledgeObtained(dKnowledgeObtained.getText());
                day.setRecapStory(dStoryOfTheDay.getText());
                day.setRating(rating);
                day.setTags(tagsList);
                day.setImageOne(imageOnePath);
                day.setImageTwo(imageTwoPath);
                day.setImageOneRotation(imageViewOne.getRotate());
                day.setImageTwoRotation(imageViewTwo.getRotate());

                dc.addDay(day.getDate().toString(), day);

                saveObject(dc, "daysCollection.ser");
                Thread.sleep(500);
                updateMessage("Done!");
                Thread.sleep(1000);
                return null;
            }
        };
        saveButton.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(e -> {
            saveButton.textProperty().unbind();
            // this message will be seen.
            saveButton.setText("Save your day");
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
