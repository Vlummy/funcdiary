package timeline.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class BackCardView {
    // Fields fot this class
    private HBox view = new HBox();
    private ImageView imageView1;
    private ImageView imageView2;

    /**
     * Construct a BackCardView with two ImageViews
     */
    public BackCardView() {
        // Set default style
        getView().setMaxSize(1200, 600);
        getView().setMinSize(600, 300);
        getView().setPrefSize(1000, 500);
        getView().setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8;");
        // Set imageViews
        setImageView1(new ImageView());
        setImageView2(new ImageView());
        // Set ID's for ImageViews
        getImageView1().setId("BackCardImageView1");
        getImageView2().setId("BackCardImageView2");
        // Set Preserve Image Ratio
        getImageView1().setPreserveRatio(true);
        getImageView2().setPreserveRatio(true);
        // Add ImageViews to HBox
        getView().getChildren().addAll(getImageView1(), getImageView2());
        getView().setAlignment(Pos.CENTER);
        getImageView1().setFitWidth(500);
        getImageView1().setFitHeight(500);
        getImageView2().setFitWidth(500);
        getImageView2().setFitHeight(500);
    }

    public HBox getView() {
        return view;
    }

    public ImageView getImageView1() {
        return imageView1;
    }

    public void setImageView1(ImageView imageView1) {
        this.imageView1 = imageView1;
    }

    public ImageView getImageView2() {
        return imageView2;
    }

    public void setImageView2(ImageView imageView2) {
        this.imageView2 = imageView2;
    }

    public void setImage1(Image image) {
        getImageView1().setImage(image);
    }

    public void setImage2(Image image) {
        getImageView2().setImage(image);
    }

    public void setImageRotation1(double degree) {
        getImageView1().setRotate(degree);
        /*if(getImageView1().getRotate() == 90 || getImageView1().getRotate() == 180) {
            getImageView1().fitWidthProperty().bind(getView().heightProperty());
            getImageView1().fitHeightProperty().bind(getView().widthProperty());
        } else {
            getImageView1().fitWidthProperty().bind(getView().widthProperty());
            getImageView1().fitHeightProperty().bind(getView().heightProperty());
        }*/
    }

    public void setImageRotation2(double degree) {
        getImageView2().setRotate(degree);
        /*if(getImageView2().getRotate() == 90 || getImageView1().getRotate() == 180) {
            getImageView2().fitWidthProperty().bind(getView().heightProperty());
            getImageView2().fitHeightProperty().bind(getView().widthProperty());
        } else {
            getImageView2().fitWidthProperty().bind(getView().widthProperty());
            getImageView2().fitHeightProperty().bind(getView().heightProperty());
        }*/
    }
}
