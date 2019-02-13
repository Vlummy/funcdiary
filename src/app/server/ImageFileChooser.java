package app.server;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.*;

public class ImageFileChooser {
    private FileChooser fileChooser;
    private Stage stage;
    private File file;

    // Target folder where the image is put
    private Path target;

    public ImageFileChooser(FileChooser fileChooser, Stage stage) {
        // Set the fields
        this.stage = stage;
        this.fileChooser = fileChooser;
        setExtensionFilters(fileChooser);

        this.setFile(fileChooser.showOpenDialog(stage));

        //moveSelectedFileToResourceFolder();

    }

    /*public void moveSelectedFileToResourceFolder() {
        // Move selected file to resource folder
        if(getFile() != null) {
            Path moveFrom = FileSystems.getDefault().getPath(getFile().getPath());
            this.target = Paths.get("img/" + getFile().getName());
            try {
                Files.copy(moveFrom, getTargetPath());
            } catch (IOException e) {

            }
        }
    }*/

    private void setExtensionFilters(FileChooser fileChooser) {
        // Set the extension filter to img files
        FileChooser.ExtensionFilter imgExtension = new FileChooser.ExtensionFilter(
                "Image files", "*.jpg", "*.jpeg", "*.png"
        );
        fileChooser.getExtensionFilters().add(imgExtension);
    }


    public Path getTargetPath() {
        return target;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public Stage getStage() {
        return stage;
    }
}
