package app.server;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Day implements Serializable {

    private ArrayList<String> tags;
    private final int maxRateLimit = 5;
    private final int minRateLimit = 1;
    private int rating;
    private String title;
    private LocalDate date;
    private String recapStory;
    private String crucialExperience;
    private String personalExperience;
    private String knowledgeObtained;
    private String imageOne;
    private String imageTwo;
    private double imageOneRotation;
    private double imageTwoRotation;

    public Day() {

    }

    public void setRating(int rating) {
        if(rating > maxRateLimit || rating < minRateLimit) {
            System.out.println("Rating has to be an integer between 1 and 5");
        } else {
            this.rating = rating;
        }
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRecapStory() {
        return recapStory;
    }

    public void setRecapStory(String recapStory) {
        this.recapStory = recapStory;
    }

    public String getCrucialExperience() {
        return crucialExperience;
    }

    public void setCrucialExperience(String crucialExperience) {
        this.crucialExperience = crucialExperience;
    }

    public String getPersonalExperience() {
        return personalExperience;
    }

    public void setPersonalExperience(String personalExperience) {
        this.personalExperience = personalExperience;
    }

    public String getKnowledgeObtained() {
        return knowledgeObtained;
    }

    public void setKnowledgeObtained(String knowledgeObtained) {
        this.knowledgeObtained = knowledgeObtained;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public double getImageOneRotation() {
        return imageOneRotation;
    }

    public void setImageOneRotation(double imageOneRotation) {
        this.imageOneRotation = imageOneRotation;
    }

    public double getImageTwoRotation() {
        return imageTwoRotation;
    }

    public void setImageTwoRotation(double imageTwoRotation) {
        this.imageTwoRotation = imageTwoRotation;
    }
}
