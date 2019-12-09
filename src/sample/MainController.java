package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// TODO: FIX ALL ENCAPSULATION

public class MainController {

    ObservableList<String> calculatorBoxList =
            FXCollections.observableArrayList();

    // "Planned Value (PV)", "Earned Value (EV)", "Actual Cost (AC)",
    //                                                    "Budget at Completion(BAC)", "Schedule Variance (SV)",
    //                                                    "Schedule Performance Index (SPI)", "Cost Variance (CV)",
    //                                                    "Cost Performance Index (CPI)"

    @FXML
    private javafx.scene.control.Button exitApp;
    @FXML
    private Button registerMemberB;
    @FXML
    private JTextField firstNameRegister;
    @FXML
    private JTextField lastNameRegister;
    @FXML
    private JTextField dobRegister;
    @FXML
    private ChoiceBox<String> calculatorBox;
    //calculatorBox.setValue("Planned Value (PV)");
    //calculatorBox.setItems(calculatorBoxList);

    /*public void initialize(){
        loadData();
    }*/

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


    // Code goes into here, whatever you want to happen when you search.
    public void search(){
        System.out.println("lmao");
    }

    public void addMember(ActionEvent event) throws IOException{

        Parent addMemberParent = FXMLLoader.load(getClass().getResource("userInfo.fxml"));
        Scene addMemberScene = new Scene(addMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(addMemberScene);
        window.show();
    }

    public void registerMember(ActionEvent event) throws IOException{

    }

    @FXML
    public void viewMember(ActionEvent event) throws IOException {

        Parent viewMemberParent = FXMLLoader.load(getClass().getResource("userInfo.fxml"));
        Scene viewMemberScene = new Scene(viewMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewMemberScene);
        window.show();
    }

    public void editEmployee(ActionEvent event) throws IOException{

        Parent editMemberParent = FXMLLoader.load(getClass().getResource("addMemberContribution.fxml"));
        Scene editMemberScene = new Scene(editMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(editMemberScene);
        window.show();

    }

    public void editInfo(ActionEvent event) throws IOException{

        Parent editInfoMemberParent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene editInfoMemberScene = new Scene(editInfoMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(editInfoMemberScene);
        window.show();
    }

    public void backButtonView(ActionEvent event) throws IOException{
        Parent backMemberParent = FXMLLoader.load(getClass().getResource("userInfo.fxml"));
        Scene backMemberScene = new Scene(backMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(backMemberScene);
        window.show();
    }

    @FXML
    public void projectOverview(ActionEvent event) throws IOException{

        Parent projectMemberParent = FXMLLoader.load(getClass().getResource("projectOverview.fxml"));
        Scene projectMemberScene = new Scene(projectMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(projectMemberScene);
        window.show();

    }

    public void contactUs(ActionEvent event) throws IOException{
        Parent contactUsParent = FXMLLoader.load(getClass().getResource("contactUs.fxml"));
        Scene contactUsScene = new Scene(contactUsParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(contactUsScene);
        window.show();
    }

    @FXML
    public void displayValue(){

    }

    // This code is for all back buttons that go back to the dashboard.
    @FXML
    public void back(ActionEvent event) throws IOException {

        Parent backMemberParent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene backMemberScene = new Scene(backMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(backMemberScene);
        window.show();
    }

    public void exit(){
        Stage stage = (Stage) exitApp.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
