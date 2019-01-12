import client.Draggable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.ImageFileChooser;

public class Main extends Application {
    private Draggable draggable = new Draggable();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("client/Diary.fxml"));
        // Make transparent screen draggable
        draggable.makeDraggable(primaryStage, root);

        Scene landingPage = new Scene(root);


        primaryStage.initStyle(StageStyle.TRANSPARENT);

        landingPage.setFill(Color.color(0, 0, 0, 0.0));

        primaryStage.setTitle("Your Functional Diary");
        primaryStage.setScene(landingPage);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

