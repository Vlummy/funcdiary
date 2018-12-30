package client;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.*;

import java.util.Optional;

import static server.SaveLoadObjectsToFile.loadObject;
import static server.SaveLoadObjectsToFile.saveObject;

/**
 * Class: MainController
 * Purpose: Controller for the Diary scene
 */
public class MainController implements Controller {
    @FXML private Label ratingLabel;
    @FXML private ChoiceBox daySelector;
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
    private int rating = 3;

    @FXML private Tab funcTab;

    /**
     * Initialize the scene
     */
    public void initialize() {
        String selectedDay = daySelector.getValue().toString();
        ratingLabel.setText("How " + selectedDay.toLowerCase() + " was your day?");
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
        resetRatingButtonColoring();
        resetDiaryInputFields();
    }

    /**
     * Method: resetDiaryInputFields()
     * Purpose: Reset the input fields of the diary to original prompt text on runtime
     */
    private void resetDiaryInputFields() {
        this.daySelector.setValue("Neutral");
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
        try {
            DaysCollection dc = (DaysCollection) loadObject("daysCollection.ser");
            System.out.println(dc.getDayCollection().keySet());
            if(dc.getDay(dPicker.getValue().toString()) != null) {
                Day day = dc.getDay(dPicker.getValue().toString());
                this.daySelector.setValue(day.getType());
                this.dTitle.setText(day.getTitle());
                this.dPicker.setPromptText(day.getDate().toString());
                this.dCrucialExperience.setText(day.getCrucialExperience());
                this.dPersonalExperience.setText(day.getPersonalExperience());
                this.dKnowledgeObtained.setText(day.getKnowledgeObtained());
                this.dStoryOfTheDay.setText(day.getRecapStory());
                this.rating = day.getRating();

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
            System.out.println("Can't find any collection of saved days, or the date is not specified");
        }
    }

    /**
     * Method: saveDay()
     * Purpose: Creates a new day and and possibly a new hashmap collection for storing days iff one not already exits.
     * The new day is saved to the collection by the date as key and day as value.
     */
    public void saveDay() {
        Task<Void> task = new Task<Void>() {
            @Override public Void call() throws Exception {
                updateMessage("Saving.");
                Day day = DayFactory.createDay(DayType.NEUTRAL);
                DaysCollection dc;
                if(daySelector.getValue().toString().equals("Bad")) {
                    day = DayFactory.createDay(DayType.BAD);
                }
                if(daySelector.getValue().toString().equals("Boring")) {
                    day = DayFactory.createDay(DayType.BORING);
                }
                if(daySelector.getValue().toString().equals("Exciting")) {
                    day = DayFactory.createDay(DayType.EXCITING);
                }
                if(daySelector.getValue().toString().equals("Good")) {
                    day = DayFactory.createDay(DayType.GOOD);
                }
                if(daySelector.getValue().toString().equals("Lonely")) {
                    day = DayFactory.createDay(DayType.LONELY);
                }
                if(daySelector.getValue().toString().equals("Mindful")) {
                    day = DayFactory.createDay(DayType.MINDFUL);
                }
                if(daySelector.getValue().toString().equals("Neutral")) {
                    day = DayFactory.createDay(DayType.NEUTRAL);
                }
                if(daySelector.getValue().toString().equals("Productive")) {
                    day = DayFactory.createDay(DayType.PRODUCTIVE);
                }
                if(daySelector.getValue().toString().equals("Sad")) {
                    day = DayFactory.createDay(DayType.SAD);
                }
                if(daySelector.getValue().toString().equals("Scary")) {
                    day = DayFactory.createDay(DayType.SCARY);
                }
                if(daySelector.getValue().toString().equals("Stressful")) {
                    day = DayFactory.createDay(DayType.STRESSFUL);
                }
                if(daySelector.getValue().toString().equals("Surprising")) {
                    day = DayFactory.createDay(DayType.SURPRISING);
                }
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

    /**
     * updateAppDescriptio()
     * Does: Updates the description fields of the diary depending on the selected day
     * Purpose: Event handler for ChoiceBox
     */
    public void updateAppDescription() {
        // Rating label update
        String selectedDay = daySelector.getValue().toString().toLowerCase();
        ratingLabel.setText("How " + selectedDay + " was your day?");

        // Textfield prompt text update
        dCrucialExperience.setPromptText("Crucial Experience: What was it that made your day " + selectedDay + "?");
    }
}
