package client;

import client.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.DayCalculator;
import server.DayType;
import server.DaysCollection;
import server.SaveLoadObjectsToFile;

import java.io.File;
import java.time.Month;
import java.util.Calendar;

public class FuncController implements Controller {
    @FXML private Label neutralAvg;
    @FXML private Label goodAvg;
    @FXML private Label excitingAvg;
    @FXML private Label mindfulAvg;
    @FXML private Label surprisingAvg;
    @FXML private Label productiveAvg;
    @FXML private Label boringAvg;
    @FXML private Label badAvg;
    @FXML private Label lonelyAvg;
    @FXML private Label sadAvg;
    @FXML private Label scaryAvg;
    @FXML private Label stressfulAvg;

    @FXML private Label neutralCount;
    @FXML private Label goodCount;
    @FXML private Label excitingCount;
    @FXML private Label mindfulCount;
    @FXML private Label surprisingCount;
    @FXML private Label productiveCount;
    @FXML private Label boringCount;
    @FXML private Label badCount;
    @FXML private Label lonelyCount;
    @FXML private Label sadCount;
    @FXML private Label scaryCount;
    @FXML private Label stressfulCount;
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
        System.out.println(year);
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        Month[] monthEnums = {Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER};
        CategoryAxis xAxis = new CategoryAxis(months);

        NumberAxis yAxis = new NumberAxis(1, 5, 1);

        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setStyle("-fx-pref-width: 800px");

        XYChart.Series neutralSeries = new XYChart.Series();
        XYChart.Series goodSeries = new XYChart.Series();
        XYChart.Series badSeries = new XYChart.Series();
        XYChart.Series excitingSeries = new XYChart.Series();
        XYChart.Series surprisingSeries = new XYChart.Series();
        XYChart.Series mindfulSeries = new XYChart.Series();
        XYChart.Series stressfulSeries = new XYChart.Series();
        XYChart.Series scarySeries = new XYChart.Series();
        XYChart.Series lonelySeries = new XYChart.Series();
        XYChart.Series sadSeries = new XYChart.Series();
        XYChart.Series productiveSeries = new XYChart.Series();
        XYChart.Series boringSeries = new XYChart.Series();
        neutralSeries.setName("Neutral");
        goodSeries.setName("Good");
        badSeries.setName("Bad");
        excitingSeries.setName("Exciting");
        surprisingSeries.setName("Surprising");
        mindfulSeries.setName("Mindful");
        stressfulSeries.setName("StressFul");
        scarySeries.setName("Scary");
        lonelySeries.setName("Lonely");
        sadSeries.setName("Sad");
        productiveSeries.setName("Productive");
        boringSeries.setName("Boring");

        for(int i = 0; i < months.size(); i++) {
            calculateAverageByMonth(neutralSeries, months.get(i), DayType.NEUTRAL, monthEnums[i], year);
            calculateAverageByMonth(goodSeries, months.get(i), DayType.GOOD, monthEnums[i], year);
            calculateAverageByMonth(badSeries, months.get(i), DayType.BAD, monthEnums[i], year);
            calculateAverageByMonth(excitingSeries, months.get(i), DayType.EXCITING, monthEnums[i], year);
            calculateAverageByMonth(surprisingSeries, months.get(i), DayType.SURPRISING, monthEnums[i], year);
            calculateAverageByMonth(mindfulSeries, months.get(i), DayType.MINDFUL, monthEnums[i], year);
            calculateAverageByMonth(stressfulSeries, months.get(i), DayType.STRESSFUL, monthEnums[i], year);
            calculateAverageByMonth(scarySeries, months.get(i), DayType.SCARY, monthEnums[i], year);
            calculateAverageByMonth(lonelySeries, months.get(i), DayType.LONELY, monthEnums[i], year);
            calculateAverageByMonth(sadSeries, months.get(i), DayType.SAD, monthEnums[i], year);
            calculateAverageByMonth(productiveSeries, months.get(i), DayType.PRODUCTIVE, monthEnums[i], year);
            calculateAverageByMonth(boringSeries, months.get(i), DayType.BORING, monthEnums[i], year);

        }

        lineChart.getData().addAll(
                neutralSeries,
                goodSeries,
                badSeries,
                excitingSeries,
                surprisingSeries,
                mindfulSeries,
                stressfulSeries,
                scarySeries,
                lonelySeries,
                sadSeries,
                productiveSeries,
                boringSeries
        );

        lineChartContainer.getChildren().add(lineChart);
    }

    /**
     * Method is used for LineChart
     * @param series
     * @param lineChartMonth
     */
    private void calculateAverageByMonth(XYChart.Series series, String lineChartMonth, DayType type, Month month, int year) {
        series.getData().add(new XYChart.Data(lineChartMonth, DayCalculator.averageByMonth(this.collection, type, month, year)));
    }

    public void calculateCount() throws Exception {
        this.collection = (DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser");

        // Calculate the average ratings of the days
        if(!collection.getDayCollection().isEmpty()) {
            this.neutralCount.setText(((Integer) DayCalculator.count(collection, DayType.NEUTRAL)).toString());
            this.goodCount.setText(((Integer)DayCalculator.count(collection, DayType.GOOD)).toString());
            this.excitingCount.setText(((Integer)DayCalculator.count(collection, DayType.EXCITING)).toString());
            this.mindfulCount.setText(((Integer)DayCalculator.count(collection, DayType.MINDFUL)).toString());
            this.surprisingCount.setText(((Integer)DayCalculator.count(collection, DayType.SURPRISING)).toString());
            this.productiveCount.setText(((Integer)DayCalculator.count(collection, DayType.PRODUCTIVE)).toString());
            this.boringCount.setText(((Integer)DayCalculator.count(collection, DayType.BORING)).toString());
            this.badCount.setText(((Integer)DayCalculator.count(collection, DayType.BAD)).toString());
            this.lonelyCount.setText(((Integer)DayCalculator.count(collection, DayType.LONELY)).toString());
            this.sadCount.setText(((Integer)DayCalculator.count(collection, DayType.SAD)).toString());
            this.scaryCount.setText(((Integer)DayCalculator.count(collection, DayType.SCARY)).toString());
            this.stressfulCount.setText(((Integer)DayCalculator.count(collection, DayType.STRESSFUL)).toString());
        }
    }

    public void calculateAverage() throws Exception {
        // Load collection
        this.collection = (DaysCollection) SaveLoadObjectsToFile.loadObject("daysCollection.ser");

        // Calculate the average ratings of the days
        if(!collection.getDayCollection().isEmpty()) {
            this.neutralAvg.setText(Double.toString(DayCalculator.average(collection, DayType.NEUTRAL)));
            this.goodAvg.setText(Double.toString(DayCalculator.average(collection, DayType.GOOD)));
            this.excitingAvg.setText(Double.toString(DayCalculator.average(collection, DayType.EXCITING)));
            this.mindfulAvg.setText(Double.toString(DayCalculator.average(collection, DayType.MINDFUL)));
            this.surprisingAvg.setText(Double.toString(DayCalculator.average(collection, DayType.SURPRISING)));
            this.productiveAvg.setText(Double.toString(DayCalculator.average(collection, DayType.PRODUCTIVE)));
            this.boringAvg.setText(Double.toString(DayCalculator.average(collection, DayType.BORING)));
            this.badAvg.setText(Double.toString(DayCalculator.average(collection, DayType.BAD)));
            this.lonelyAvg.setText(Double.toString(DayCalculator.average(collection, DayType.LONELY)));
            this.sadAvg.setText(Double.toString(DayCalculator.average(collection, DayType.SAD)));
            this.scaryAvg.setText(Double.toString(DayCalculator.average(collection, DayType.SCARY)));
            this.stressfulAvg.setText(Double.toString(DayCalculator.average(collection, DayType.STRESSFUL)));
        }
    }
}
