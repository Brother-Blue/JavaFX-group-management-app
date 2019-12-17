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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
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
    public void calcSelection() {
        String selection = (String) calculatorDropdown.getValue();
        if (selection == null){
            //error popup here
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
        calcDescrip.setText("");
        double result = planner.calcPv();
        calcResult.setText("" + result);
        calcFormula.setText("");
    }
    public void earnedValue(){
        calcDescrip.setText("Expenditures that should have been realised given the actual technical project progress (based on\n" +
                "the expenditure plan) (From lecture slides)");
        double result = planner.calcEv();
        calcResult.setText("" + result);
        calcFormula.setText("");
    }
    public void actualCost(){
        calcDescrip.setText("");
        double result = planner.calcActualCost();
        calcResult.setText("" + result);
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
        calcResult.setText("" + result);
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
        calcResult.setText("" + result);
        calcFormula.setText("EV – AV");
    }
    public void costPerfIndex(){
        calcDescrip.setText("");
        calcResult.setText("To be implemented");
        calcFormula.setText("(EV / AV)*100");
    }

    }

