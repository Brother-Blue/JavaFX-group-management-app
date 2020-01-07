package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import member_manager.Member;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectOverviewController extends MainController implements Initializable {

@FXML
private ComboBox calculatorDropdown;
@FXML
private TextField calcResult;
@FXML
private TextArea calcFormula;
@FXML
private TextArea calcDescrip;
@FXML
private AreaChart areaChart;
@FXML
private CategoryAxis xAxis;
@FXML
private NumberAxis yAxis;
@FXML
private Button searchButton;

    private String currentWeek = Integer.toString(planner.calcWeek());

    public void riskMatrixView(ActionEvent event) throws IOException {
        Parent riskMatrixMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/riskMatrix.fxml"));
        Scene riskMatrixMemberScene = new Scene(riskMatrixMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(riskMatrixMemberScene);
        window.show();
    }

    public void projectScheduleView(ActionEvent event) throws IOException{
        Parent projectScheduleParent = FXMLLoader.load(getClass().getResource("../fxml-files/projectSchedule.fxml"));
        Scene projectScheduleScene = new Scene(projectScheduleParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(projectScheduleScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<String> calcOptions = FXCollections.observableArrayList();
            calcOptions.addAll("Planned Value (PV)", "Earned Value (EV)", "Actual Cost (AC)",
                    "Budget at Completion (BAC)", "Schedule Variance (SV)",
                    "Schedule Performance Index (SPI)", "Cost Variance (CV)",
                    "Cost Performance Index (CPI)");
            calculatorDropdown.setItems(calcOptions);

            xAxis.setAnimated(false);
            yAxis.setAnimated(false);
        }

    public void calcSelection(ActionEvent actionEvent) {
        Window owner = calculatorDropdown.getScene().getWindow();
        String selection = calculatorDropdown.getValue().toString();

        if (selection == null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please select an option");
        } else if (selection == "Planned Value (PV)"){
           plannedValue();
        } else if (selection == "Earned Value (EV)"){
           earnedValue();
        } else if (selection == "Actual Cost (AC)") {
            actualCost();
        } else if (selection ==  "Budget at Completion (BAC)") {
            budgetAtCompl();
        } else if (selection == "Schedule Variance (SV)") {
            scheduleVariance();
        } else if (selection == "Schedule Performance Index (SPI)") {
            schedulePerfIndex();
        } else if (selection == "Cost Variance (CV)") {
            costVariance();
        } else if (selection == "Cost Performance Index (CPI)"){
            costPerfIndex();
        }
    }

    public void plannedValue(){
        searchButton.setDisable(true);
        ArrayList<Double> results = planner.calcPv();
        XYChart.Series pvSeries = new XYChart.Series();
        areaChart.getData().clear();

        calcDescrip.setText("Planned value is the relation between the amount of days a current milestone has been worked on and the current budget. " +
                "The current days are measured from when the milestone started up to the current day."); //calcWeek - 1 brings the graph value thats displayed  ti the correct value.
        calcResult.setText("Current week: " + currentWeek + ", Planned Value: " + "SEK");
        calcFormula.setText("Planned Value = Days worked / Total days worked (Expected)");

        pvSeries.setName("PV Values");
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        for (int i = 0; i < results.size(); i++) {
            pvSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));
        }
        areaChart.getData().add(pvSeries);

    }
    public void earnedValue(){
        ArrayList<Double> results = planner.calcEv();
        searchButton.setDisable(true);
        XYChart.Series evSeries = new XYChart.Series();
        areaChart.getData().clear();

        calcDescrip.setText("Expenditures that should have been realised given the actual technical project progress (based on\n" +
                "the expenditure plan) (From lecture slides)");
        calcResult.setText("If you see this it means Chris forgot to fix this part :)");
        calcFormula.setText("");

        evSeries.setName("Earned Value");
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        for (int i = 0; i < results.size(); i++) {
            evSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));
        }

        areaChart.getData().add(evSeries);

    }
    public void actualCost(){
        ArrayList<Double> results = planner.calcActualCost();
        searchButton.setDisable(true);
        XYChart.Series acSeries = new XYChart.Series();
        areaChart.getData().clear();

        double actual = 0;
        for (Member member : planner.members) {
            actual = actual + planner.getTotalSalaryForMember(member.getId());
        }

        calcDescrip.setText("Actual Cost is how much was paid at a certain period of time. \n(In our case costs is only salary).");
        calcResult.setText("Total costs: " + actual + "SEK");
        calcFormula.setText("Actual Cost = Budget - Costs.");

        acSeries.setName("Actual Costs");
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        for (int i = 0; i < results.size(); i++) {
            acSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));
        }

        areaChart.getData().add(acSeries);

    }
    public void budgetAtCompl(){
        ArrayList<Double> results = planner.calcBAC();
        searchButton.setDisable(true);
        XYChart.Series bacSeries = new XYChart.Series();
        areaChart.getData().clear();

        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("");

        bacSeries.setName("Budget at Completion - (Week)");
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        for (int i = 0; i < results.size(); i++) {
            bacSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));
        }

        areaChart.getData().add(bacSeries);
    }
    public void scheduleVariance(){
        ArrayList<Double> results = planner.calcSv();
        searchButton.setDisable(true);
        XYChart.Series svSeries = new XYChart.Series();
        areaChart.getData().clear();

        calcDescrip.setText("Difference between planned expenditures and earned value (From lecture slides)");
        //double result = planner.calcSv();
        //calcResult.setText(Double.toString(result));
        calcFormula.setText("EV – PV");

        svSeries.setName("Scheduled Variance");
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        for (int i = 0; i < results.size(); i++) {
            svSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));
            System.out.println(results.get(i));
        }

        areaChart.getData().add(svSeries);

    }
    public void schedulePerfIndex(){
        ArrayList<Double> results = planner.calcSPI();
        searchButton.setDisable(true);
        XYChart.Series spiSeries = new XYChart.Series();
        areaChart.getData().clear();

        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("(EV / PV)*100");

        spiSeries.setName("Scheduled Performance Index");
        xAxis.setLabel("Week");
        yAxis.setLabel("Index");

        for (int i = 0; i < results.size(); i++) {
            spiSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));
        }

        areaChart.getData().add(spiSeries);
    }
    public void costVariance(){
        ArrayList<Double> results = planner.calcCv();
        searchButton.setDisable(true);
        XYChart.Series cvSeries = new XYChart.Series();
        areaChart.getData().clear();

        calcDescrip.setText("Difference between actual expenditures and earned value (From lecture slides)");
        double result = 0;
        calcResult.setText(Double.toString(result));
        calcFormula.setText("EV – AV");

        cvSeries.setName("Cost Variance");
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        for (int i = 0; i < results.size(); i++) {
            cvSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));
        }

        areaChart.getData().add(cvSeries);
    }
    public void costPerfIndex(){
        ArrayList<Double> results = planner.calcCPI();
        searchButton.setDisable(true);
        XYChart.Series cpiSeries = new XYChart.Series();
        areaChart.getData().clear();

        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("(EV / AV)");

        cpiSeries.setName("Cost Performance Index");
        xAxis.setLabel("Week");
        yAxis.setLabel("Index");

        for (int i = 0; i < results.size(); i++) {
            cpiSeries.getData().add(new XYChart.Data(Integer.toString(i+1), results.get(i)));

        }

        areaChart.getData().add(cpiSeries);
    }

    public void enableSearch(ActionEvent actionEvent) {
        searchButton.setDisable(false);
    }
}

