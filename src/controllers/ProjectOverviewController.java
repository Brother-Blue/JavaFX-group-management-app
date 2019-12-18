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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
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
private LineChart lineChart;
@FXML
private CategoryAxis xAxis;
@FXML
private NumberAxis yAxis;
@FXML
private Button submitButton;



    public void riskMatrixView(ActionEvent event) throws IOException {
        Parent riskMatrixMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/riskMatrix.fxml"));
        Scene riskMatrixMemberScene = new Scene(riskMatrixMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(riskMatrixMemberScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<String> calcOptions = FXCollections.observableArrayList();
            calcOptions.addAll("Planned Value (PV)", "Earned Value (EV)", "Actual Cost (AC)",
                    "Budget at Completion(BAC)", "Schedule Variance (SV)",
                    "Schedule Performance Index (SPI)", "Cost Variance (CV)",
                    "Cost Performance Index (CPI)");
            calculatorDropdown.setItems(calcOptions);

        }
    public void calcSelection(ActionEvent actionEvent) {
        Window owner = submitButton.getScene().getWindow();
        String selection = (String) calculatorDropdown.getValue();
        if (selection == null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please select an option");
        } else if (selection == "Planned Value (PV)"){
           plannedValue();
        } else if (selection == "Earned Value (EV)"){
           earnedValue();
        } else if (selection == "Actual Cost (AC)") {
            actualCost();
        } else if (selection ==  "Budget at Completion(BAC)") {
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
        calcDescrip.setText("Planned value is the relation between the amount of days a current milestone has been worked on and the current budget. " +
                "The current days are measured from when the milestone started up to the current day.");
        double result = 0;
        calcResult.setText(Double.toString(result));
        calcFormula.setText("Planned Value = Days worked / Total days worked (Expected)");

        XYChart.Series pvSeries = new XYChart.Series();

        for (int i = 0; i < planner.milestones.size(); i++) {
            result = planner.calcPv();
            pvSeries.getData().add(new XYChart.Data<>(Integer.toString(i+1), result));
            System.out.println(result);
        }

        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        lineChart.setTitle("Planned Values (PV)");
        lineChart.getData().add(pvSeries);
    }
    public void earnedValue(){
        calcDescrip.setText("Expenditures that should have been realised given the actual technical project progress (based on\n" +
                "the expenditure plan) (From lecture slides)");
        double result = planner.calcEv();
        calcResult.setText(Double.toString(Math.round(result*100.00)/100.00));
        calcFormula.setText("");
    }
    public void actualCost(){
        calcDescrip.setText("");
        double result = planner.calcActualCost();
        calcResult.setText(Double.toString(result));
        calcFormula.setText("");
    }
    public void budgetAtCompl(){
        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("");
    }
    public void scheduleVariance(){
        calcDescrip.setText("Difference between planned expenditures and earned value (From lecture slides)");
        double result = planner.calcSv();
        calcResult.setText(Double.toString(result));
        calcFormula.setText("EV – PV");
    }
    public void schedulePerfIndex(){
        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("(EV / PV)*100");
    }
    public void costVariance(){
        calcDescrip.setText("Difference between actual expenditures and earned value (From lecture slides)");
        double result = planner.calcCv();
        calcResult.setText(Double.toString(result));
        calcFormula.setText("EV – AV");
    }
    public void costPerfIndex(){
        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("(EV / AV)*100");
    }

    }

