package timeline;

import server.Day;

import java.util.ArrayList;

/**
 * Domain spesific class for this app (Functional Diary)
 * This class takes in a ArrayList of containing days from the Day class and sets the different fields needed
 * to always keep track of the current, previous and next date in line when scrolling through the
 * the cards a timeline displays.
 *
 * This class implements LoaderInterface to handle the main controls of loading and updating these
 * fields in a correct manner.
 */
public class DayLoader implements LoaderInterface {

    // Fields for this class
    private ArrayList<Day> daysList;
    private String selectedDate = null;
    private String currentLoadedDate = null;
    private String previousDate = null;
    private String nextDate = null;

    /**
     * Constructor: Empty
     */
    public DayLoader() {}

    /**
     * Constructor: builds a initial loader state where current loaded date is set to newest entry
     * and then previous and next date is also set if there are any.
     * @param daysList
     */
    public DayLoader(ArrayList<Day> daysList) {
        this.daysList = daysList;
        this.loadNewestEntry();
    }

    @Override
    public boolean hasSpecific() {
        boolean isAvailable = false;
        // If selectedDate has value, isAvailable = true
        if(selectedDate != null)
            isAvailable = true;
        // If specificDate does not have value, isAvailable = false
        return isAvailable;
    }

    @Override
    public boolean hasNext() {
        boolean isAvailable = false;
        // If nextDate has value, isAvailable = true
        if(nextDate != null)
            isAvailable = true;
        // If nextDate does not have value, isAvailable = false
        return isAvailable;
    }

    @Override
    public boolean hasPrevious() {
        boolean isAvailable = false;
        // If nextDate has value, isAvailable = true
        if(previousDate != null)
            isAvailable = true;
        // If nextDate does not have value, isAvailable = false
        return isAvailable;
    }

    @Override
    public void loadNewestEntry() {
        // Set currentLoadedDate to newest entry ( filter the newest date of all the days )

        // Set previousDate ( uses currentLoadedDate )

        // Set nextDate ( uses currentLoadedDate )

        // Set selectedEntry ( uses currentLoadedDate )
    }

    @Override
    public void loadSpecificEntry() {
        // If hasSpecific

        // Set currentLoadedDate ( from selectedDate )

        // Set previousDate ( uses currentLoadedDate )

        // Set nextDate ( uses currentLoadedDate )
    }

    @Override
    public void loadPreviousEntry() {
        // If hasPrevious

        // Set currentLoadedDate ( from previousDate )

        // Set previousDate ( uses currentLoadedDate )

        // Set nextDate ( uses currentLoadedDate )

        // Set selectedEntry ( uses currentLoadedDate )
    }

    @Override
    public void loadNextEntry() {
        // If hasNext

        // Set currentLoadedDate ( from nextDate )

        // Set previousDate ( uses currentLoadedDate )

        // Set nextDate ( uses currentLoadedDate )

        // Set selectedEntry ( uses currentLoadedDate )
    }

    /**
     * Get daysList
     * @return daysList
     */
    public ArrayList<Day> getDaysList() {
        return daysList;
    }

    /**
     * Set daysList
     * @param daysList
     */
    public void setDaysList(ArrayList<Day> daysList) {
        this.daysList = daysList;
    }

    /**
     * Get selectedDate
     * @return
     */
    public String getSelectedDate() {
        return selectedDate;
    }

    /**
     * Set selectedDate
     * @param selectedDate
     */
    public void setSelectedDate(String selectedDate) {
        // If day list item has selected date, set the date

        // If not, set to null
        this.selectedDate = selectedDate;
    }

    /**
     * Get the current loaded date
     * @return currentLoadedDate
     */
    public String getCurrentLoadedDate() {
        return currentLoadedDate;
    }

    /**
     * Set the current loaded date
     * @param currentLoadedDate
     * This method is private because only the override methods should be able to update certain field
     */
    private void setCurrentLoadedDate(String currentLoadedDate) {
        this.currentLoadedDate = currentLoadedDate;
    }

    /**
     * Get the current loaded previous date
     * @return previousDate
     */
    public String getPreviousDate() {
        return previousDate;
    }

    /**
     * Set the previous date
     * This method is private because only the override methods should be able to update certain field
     */
    private void setPreviousDate() {
        // Decrement currentLoadedDate by 1 into a new variable

        // Check if decremented date is available in the list

        // If available, set previousDate to decremented date

        // If not, set previousDate to null
    }

    /**
     * Get the current loaded next date
     * @return nextDate
     */
    public String getNextDate() {
        return nextDate;
    }

    /**
     * Set the next date
     * This method is private because only the override methods should be able to update certain field
     */
    private void setNextDate() {
        // Increment currentLoadedDate by 1 into a new variable

        // Check if incremented date is available in the list

        // If available, set nextDate to incremented date

        // If not, set nextDate to null
    }
}
