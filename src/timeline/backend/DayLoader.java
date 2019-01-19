package timeline.backend;

import server.Day;

import java.time.LocalDate;
import java.util.*;

/**
 * Domain spesific class for this app (Functional Diary)
 * This class takes in a ArrayList that contains days from the Day class and sets the different fields needed
 * to always keep track of the current, previous and next date in line when scrolling through the
 * the cards a timeline displays.
 *
 * This class implements LoaderInterface to handle the main controls of loading and updating these
 * fields in a correct manner.
 *
 * Author: Øyvind Johannessen
 * Version: 1.0
 */
public class DayLoader implements LoaderInterface<Day, LocalDate> {

    // Fields for this class
    private ArrayList<Day> daysList;
    private LocalDate selectedDate = null;
    private LocalDate currentLoadedDate = null;
    private LocalDate previousDate = null;
    private LocalDate nextDate = null;

    /**
     * Constructor: builds a initial loader state where current loaded date is set to newest entry
     * and then previous and next date is also set if there are any.
     * @param daysCollection
     */
    public DayLoader(HashMap<String, Day> daysCollection) {
        setDaysList(daysCollection);
        loadNewestEntry();
    }

    /**
     * Sort the list so that previous and next date is correct
     */
    private void sortList() {
        getDaysList().sort(Comparator.comparing(Day::getDate));
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
        try {
            // Set currentLoadedDate to newest entry ( filter the newest date of all the days )
            LocalDate newestDate = getDaysList().stream().map(day -> day.getDate()).max(LocalDate::compareTo).get();
            setCurrentLoadedDate(newestDate);
            // Set previousDate
            setPreviousDate();
            // Set nextDate
            setNextDate();
            // Set selectedEntry
            setSelectedDate(getCurrentLoadedDate());
        } catch (NoSuchElementException e) {

        }
    }

    @Override
    public void loadSpecificEntry(LocalDate date) {
        // Set selected date
        setSelectedDate(date);
        if(hasSpecific()) {
            // Set currentLoadedDate ( from selectedDate )
            setCurrentLoadedDate(getSelectedDate());
            // Set previousDate ( uses currentLoadedDate )
            setPreviousDate();
            // Set nextDate ( uses currentLoadedDate )
            setNextDate();
        }
    }

    @Override
    public void loadPreviousEntry() {
        // If hasPrevious
        if(hasPrevious()) {
            // Set currentLoadedDate ( from previousDate )
            setCurrentLoadedDate(getPreviousDate());
            // Set previousDate ( uses currentLoadedDate )
            setPreviousDate();
            // Set nextDate ( uses currentLoadedDate )
            setNextDate();
            // Set selectedEntry ( uses currentLoadedDate )
        }
    }

    @Override
    public void loadNextEntry() {
        // If hasNext
        if(hasNext()) {
            // Set currentLoadedDate ( from nextDate )
            setCurrentLoadedDate(getNextDate());
            // Set previousDate
            setPreviousDate();
            // Set nextDate
            setNextDate();
            // Set selectedEntry
        }
    }

    @Override
    public Day getEntry() {
        // Find the correct day in the daysList ( Uses currentLoadedDate )
        Day day = null;
        for(Day item : getDaysList()) {
            if(item.getDate().equals(getCurrentLoadedDate()))
                day = item;
        }
        // Return it
        return day;
    }

    /**
     * Get daysList
     * @return daysList
     * This method is private because loader should only handle and return one day at a time
     */
    private ArrayList<Day> getDaysList() {
        return daysList;
    }

    /**
     * Set daysList
     * @param daysList
     * This method is private because loader should only deal with one list at a time
     */
    private void setDaysList(HashMap<String, Day> daysList) {
        this.daysList = new ArrayList<>();
        getDaysList().addAll(daysList.values());
        sortList();
    }

    /**
     * Get selectedDate
     * @return
     */
    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    /**
     * Set selectedDate
     * @param date
     */
    public void setSelectedDate(LocalDate date) {
        // If day list item has selected date, set the date
        for(Day day : getDaysList()) {
            if(day.getDate().equals(date)) {
                this.selectedDate = date;
            }
        }
    }

    /**
     * Get the current loaded date
     * @return currentLoadedDate
     */
    public LocalDate getCurrentLoadedDate() {
        return currentLoadedDate;
    }

    /**
     * Set the current loaded date
     * @param currentLoadedDate
     * This method is private because only the override methods should be able to update certain field
     */
    private void setCurrentLoadedDate(LocalDate currentLoadedDate) {
        this.currentLoadedDate = currentLoadedDate;
    }

    /**
     * Get the current loaded previous date
     * @return previousDate
     */
    public LocalDate getPreviousDate() {
        return previousDate;
    }

    /**
     * Set the previous date
     * This method is private because only the override methods should be able to update certain field
     */
    private void setPreviousDate() {
        // Find newest date before current loaded date
        for(int i = 0; i < getDaysList().size(); i++) {
            if(getDaysList().get(i).getDate().equals(getCurrentLoadedDate())) {
                try {
                    this.previousDate = getDaysList().get(i - 1).getDate();
                } catch (IndexOutOfBoundsException e) {
                    // Do nothing, and keep previous date as null
                }
            }
        }
    }

    /**
     * Get the current loaded next date
     * @return nextDate
     */
    public LocalDate getNextDate() {
        return nextDate;
    }

    /**
     * Set the next date
     * This method is private because only the override methods should be able to update certain field
     */
    private void setNextDate() {
        // Increment currentLoadedDate by 1 into a new variable
        for(int i = 0; i < getDaysList().size(); i++) {
            if(getDaysList().get(i).getDate().equals(getCurrentLoadedDate())) {
                try {
                    this.nextDate = getDaysList().get(i + 1).getDate();
                } catch (IndexOutOfBoundsException e) {
                    // Do nothing, and keep next date as null
                }
            }
        }
    }
}
