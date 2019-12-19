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
private BarChart barChart;
@FXML
private CategoryAxis xAxis;
@FXML
private NumberAxis yAxis;

    private String currentWeek = Integer.toString(planner.calcWeek());

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
        planner.calcPv();

        calcDescrip.setText("Planned value is the relation between the amount of days a current milestone has been worked on and the current budget. " +
                "The current days are measured from when the milestone started up to the current day."); //calcWeek - 1 brings the graph value thats displayed  ti the correct value.
        calcResult.setText("Current week: " + currentWeek + ", Planned Value: " + planner.pcpValues.get(planner.calcWeek()-1).toString() + "SEK");
        calcFormula.setText("Planned Value = Days worked / Total days worked (Expected)");

        float result;
        xAxis.setLabel("Week");

        barChart.getData().clear();
        barChart.autosize();
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < planner.pcpValues.size(); i++) {
            result = planner.pcpValues.get(i);
            series.getData().add(new XYChart.Data<>(Integer.toString(i+1), result));
        }

        barChart.setTitle("Planned Values (SEK)");
        barChart.getData().add(series);
    }
    public void earnedValue(){
        calcDescrip.setText("Expenditures that should have been realised given the actual technical project progress (based on\n" +
                "the expenditure plan) (From lecture slides)");
        double result = planner.calcEv();
        calcResult.setText(Double.toString(Math.round(result*100.00)/100.00));
        calcFormula.setText("");
    }
    public void actualCost(){
        planner.calcActualCost();

        calcDescrip.setText("Actual Cost is how much was paid at a certain period of time. \n(In our case costs is only salary).");
        double result = 0;
        calcResult.setText("Current week: " + currentWeek + ", Actual Costs: " + planner.actualCosts.get(planner.actualCosts.size()-1) + "SEK");
        calcFormula.setText("Actual Cost = Budget - Costs.");

        xAxis.setLabel("Week");

        barChart.getData().clear();
        barChart.autosize();
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < planner.actualCosts.size(); i++) {
            result = planner.actualCosts.get(i);
            series.getData().add(new XYChart.Data<>(Integer.toString(i+1), result));
        }

        barChart.setTitle("Actual Costs (SEK)");
        barChart.getData().add(series);
    }
    public void budgetAtCompl(){
        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("");
    }
    public void scheduleVariance(){
        calcDescrip.setText("Difference between planned expenditures and earned value (From lecture slides)");
        //double result = planner.calcSv();
        //calcResult.setText(Double.toString(result));
        calcFormula.setText("EV – PV");
    }
    public void schedulePerfIndex(){
        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("(EV / PV)*100");
    }
    public void costVariance(){
        calcDescrip.setText("Difference between actual expenditures and earned value (From lecture slides)");
        double result = 0;
        calcResult.setText(Double.toString(result));
        calcFormula.setText("EV – AV");
    }
    public void costPerfIndex(){
        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("(EV / AV)*100");
    }

    }

