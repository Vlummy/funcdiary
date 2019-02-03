package timeline.frontend;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * BackCardView is the the card that lies behind FrontCardView. It can contains two images.
 * This class contains an HBox that represents the actual view/layout of the card. To get the view
 * use the method getView().
 *
 * ID's for this class:
 * #backCardImageView1
 * #backCardImageView2
 * #backCardView
 *
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public class BackCardView {
    // Fields fot this class
    private HBox view = new HBox();
    private ImageView imageView1;
    private ImageView imageView2;

    /**
     * Construct a default BackCardView with two ImageViews
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
        getImageView1().setId("backCardImageView1");
        getImageView2().setId("backCardImageView2");
        getView().setId("backCardView");
        // Set Preserve Image Ratio
        getImageView1().setPreserveRatio(true);
        getImageView2().setPreserveRatio(true);
        // Add ImageViews to HBox
        Region spacer1 = new Region();
        Region spacer2 = new Region();
        Region spacer3 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        getView().getChildren().addAll(spacer1, getImageView1(), spacer2, getImageView2(), spacer3);
        getView().setAlignment(Pos.CENTER);
        getImageView1().setFitWidth(450);
        getImageView1().setFitHeight(450);
        getImageView2().setFitWidth(450);
        getImageView2().setFitHeight(450);
    }

    /**
     * Get the actual view of the back card
     * @return view
     */
    public HBox getView() {
        return view;
    }

    /**
     * Get the image view 1
     * @return imageView1
     */
    public ImageView getImageView1() {
        return imageView1;
    }

    /**
     * Set image view 1
     * @param imageView1
     */
    public void setImageView1(ImageView imageView1) {
        this.imageView1 = imageView1;
    }

    /**
     * Get image view 2
     * @return imageView2
     */
    public ImageView getImageView2() {
        return imageView2;
    }

    /**
     * Set image view 2
     * @param imageView2
     */
    public void setImageView2(ImageView imageView2) {
        this.imageView2 = imageView2;
    }

    /**
     * Add an image to imageView1
     * @param image
     */
    public void setImage1(Image image) {
        getImageView1().setImage(image);
    }

    /**
     * Set an image to imageView2
     * @param image
     */
    public void setImage2(Image image) {
        getImageView2().setImage(image);
    }

    /**
     * Set the rotation of the imageView1
     * @param degree
     */
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

    /**
     * Set the rotation of imageView 2
     * @param degree
     */
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
