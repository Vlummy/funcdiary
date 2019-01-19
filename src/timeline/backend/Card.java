package timeline.backend;

import java.util.ArrayList;

/**
 * Card is a Timeline component Class. It has the responsibility of holding and returning the information that is
 * meant to be viewed.
 * H1 to H6 is different headers the card can hold, and follows the html convention. H1 is usually the main title
 * This class also has a list of images, and a list of tags that can be viewed.
 *
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public class Card {
    // Fields for this class
    private String date;
    private String h1;
    private String h2;
    private String h3;
    private String h4;
    private String h5;
    private String h6;
    private String body;
    private ArrayList<String> tags;
    private ArrayList<String> imagePaths;

    /**
     * Constructor: Empty
     */
    public Card() {
        tags = new ArrayList<>();
        imagePaths = new ArrayList<>();
    }

    /**
     * Get the date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get header 1
     * @return h1
     */
    public String getH1() {
        return h1;
    }

    /**
     * Set header 1
     * @param h1
     */
    public void setH1(String h1) {
        this.h1 = h1;
    }

    /**
     * Get header 2
     * @return h2
     */
    public String getH2() {
        return h2;
    }

    /**
     * Set header 2
     * @param h2
     */
    public void setH2(String h2) {
        this.h2 = h2;
    }

    /**
     * Get header 3
     * @return h3
     */
    public String getH3() {
        return h3;
    }

    /**
     * Set header 3
     * @param h3
     */
    public void setH3(String h3) {
        this.h3 = h3;
    }

    /**
     * Get header 4
     * @return h4
     */
    public String getH4() {
        return h4;
    }

    /**
     * Set header 4
     * @param h4
     */
    public void setH4(String h4) {
        this.h4 = h4;
    }

    /**
     * Get header 5
     * @return h5
     */
    public String getH5() {
        return h5;
    }

    /**
     * Set header 5
     * @param h5
     */
    public void setH5(String h5) {
        this.h5 = h5;
    }

    /**
     * Get header 6
     * @return h6
     */
    public String getH6() {
        return h6;
    }

    /**
     * Set header 6
     * @param h6
     */
    public void setH6(String h6) {
        this.h6 = h6;
    }

    /**
     * Get the card body text
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the body
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get list of tags
     * @return tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Set tags list
     * @param tags
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * Get list of image paths
     * @return imagePaths
     */
    public ArrayList<String> getImagePaths() {
        return imagePaths;
    }

    /**
     * Set image paths list
     * @param imagePaths
     */
    public void setImagePaths(ArrayList<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    /**
     * Add a tag to the tags list
     * @param tag
     */
    public void addTag(String tag) {
        getTags().add(tag);
    }

    /**
     * Add a image path to image path list
     * Note: This does not check if your path is actually a path
     * @param path
     */
    public void addImagePath(String path) {
        getImagePaths().add(path);
    }

    @Override
    public String toString() {
        return "DATE:\n" + getDate() + "\n\n" + "HEADERS 1-6:\n" + getH1() + "\n" + getH2() + "\n" + getH3() + "\n" + getH4() + "\n" + getH5() + "\n" +
                getH6() + "\n\n" + "BODY:\n" + getBody() + "\n\n" + "TAGS:\n" + getTags() + "\n\n" + "IMAGE PATHS:\n" +
                getImagePaths();
    }
}
