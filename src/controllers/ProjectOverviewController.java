package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectOverviewController extends MainController {

    ObservableList<String> calculatorBoxList =
            FXCollections.observableArrayList();

    // "Planned Value (PV)", "Earned Value (EV)", "Actual Cost (AC)",
    //                                                    "Budget at Completion(BAC)", "Schedule Variance (SV)",
    //                                                    "Schedule Performance Index (SPI)", "Cost Variance (CV)",
    //                                                    "Cost Performance Index (CPI)"



    @FXML
    private ChoiceBox<String> calculatorBox;
    //calculatorBox.setValue("Planned Value (PV)");
    //calculatorBox.setItems(calculatorBoxList);


    @FXML
    public void displayValue(){

    }


    public void riskMatrixView(ActionEvent event) throws IOException {
        Parent riskMatrixMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/riskMatrix.fxml"));
        Scene riskMatrixMemberScene = new Scene(riskMatrixMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(riskMatrixMemberScene);
        window.show();
    }

    public void loadData(){
        String a = "Planned Value (PV)";
        String b = "Earned Value (EV)";
        String c = "Actual Cost (AC)";
        String d = "Budget at Completion(BAC)";
        String e = "Schedule Variance (SV)";
        String f = "Schedule Performance Index (SPI)";
        String g = "Cost Variance (CV)";
        String h = "Cost Performance Index (CPI)";

        calculatorBoxList.addAll(a,b,c,d,e,f,g,h);
        calculatorBox.getItems().addAll(calculatorBoxList);
    }


}
