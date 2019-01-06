package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.DayCalculator;
import server.DaysCollection;
import server.SaveLoadObjectsToFile;

import java.io.File;
import java.time.Month;
import java.util.Calendar;

public class FuncController implements Controller {
    @FXML private Label daysAvg;

    @FXML private Label dayCount;

    @FXML private TextField chartYearField;

    // Linechart
    @FXML private Group lineChartContainer;

    private DaysCollection collection;

    public void initialize() throws Exception {
        File file = new File("daysCollection.ser");
        if(file.exists()) {
            calculateAverage();
            calculateCount();
            createMonthAverageAxis();


        } else {
            // Make a new collection object for storing days
            SaveLoadObjectsToFile.saveObject(new DaysCollection(), "daysCollection.ser");
        }
    }

    public void validateNumbers() {
        if(chartYearField.getText().length() > 4) {
            chartYearField.setText("");
        }

        if(!chartYearField.getText().matches("[0-9]+")) {
            chartYearField.setText("");
        }
    }

    public void createMonthAverageAxis() throws Exception {
        collection = (DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser");
        calculateCount();
        calculateAverage();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if(chartYearField.getText().length() > 0) {
            year = Integer.parseInt(chartYearField.getText());
        }
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        Month[] monthEnums = {Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER};
        CategoryAxis xAxis = new CategoryAxis(months);

        NumberAxis yAxis = new NumberAxis(1, 5, 1);

        AreaChart lineChart = new AreaChart(xAxis, yAxis);
        lineChart.setStyle("-fx-pref-width: 800px");

        XYChart.Series series = new XYChart.Series();

        series.setName("Average rating calculated from ratings given each single day");



        for(int i = 0; i < months.size(); i++) {
            calculateAverageByMonth(series, months.get(i), monthEnums[i], year);
        }

        lineChart.getData().addAll(
                series
        );

        lineChartContainer.getChildren().add(lineChart);
    }

    /**
     * Method is used for LineChart
     * @param series
     * @param lineChartMonth
     */
    private void calculateAverageByMonth(XYChart.Series series, String lineChartMonth, Month month, int year) {
        series.getData().add(new XYChart.Data(lineChartMonth, DayCalculator.averageByMonth(this.collection, month, year)));
    }

    public void calculateCount() throws Exception {
        this.collection = (DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser");

        // Count of the days
        if(!collection.getDayCollection().isEmpty()) {
            this.dayCount.setText(((Integer) DayCalculator.count(collection)).toString());
        }
    }

    public void calculateAverage() throws Exception {
        // Load collection
        this.collection = (DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser");

        // Calculate the average ratings of the days
        if(!collection.getDayCollection().isEmpty()) {
            this.daysAvg.setText(Double.toString(DayCalculator.average(collection)));
        }
    }
}
