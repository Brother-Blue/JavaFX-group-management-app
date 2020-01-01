package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import json_reader_writer.JsonReader;
import json_reader_writer.JsonWriter;
import member_manager.Planner;

import java.io.IOException;

public class dashboardController extends MainController {

    @FXML
    private Button exitApp;
    @FXML
    private Button saveDataButton;
    @FXML
    private Button loadDataButton;
    @FXML
    private Button projectOverviewButton;
    @FXML
    private Button newMilestoneButton;
    @FXML
    private Button viewMemberButton;
    @FXML
    private Button newMemberButton;

    public void addMember(ActionEvent event) throws IOException {

        Parent addMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/newMember.fxml"));
        Scene addMemberScene = new Scene(addMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(addMemberScene);
        window.show();
    }

    public void viewMember(ActionEvent event) throws IOException {

        Parent viewMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/userInfo.fxml"));
        Scene viewMemberScene = new Scene(viewMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewMemberScene);
        window.show();
    }
    @FXML
    public void projectOverview(ActionEvent event) throws IOException{

        Parent projectMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/projectOverview.fxml"));
        Scene projectMemberScene = new Scene(projectMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(projectMemberScene);
        window.show();

    }

    public void contactUs(ActionEvent event) throws IOException{
        Parent contactUsParent = FXMLLoader.load(getClass().getResource("../fxml-files/contactUs.fxml"));
        Scene contactUsScene = new Scene(contactUsParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(contactUsScene);
        window.show();
    }


    public void newMilestone(ActionEvent event) throws IOException {

        Parent milestoneMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/milestones.fxml"));
        Scene milestoneMemberScene = new Scene(milestoneMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(milestoneMemberScene);
        window.show();
    }

    public void loadData(){
        JsonReader reader = new JsonReader();
       planner = reader.loadPlanner();

       saveDataButton.setDisable(false);
       newMemberButton.setDisable(false);
       newMilestoneButton.setDisable(false);
       projectOverviewButton.setDisable(false);
       viewMemberButton.setDisable(false);
       loadDataButton.setDisable(true);
    }
    public void saveState(){
        JsonWriter writer = new JsonWriter();
        writer.savePlanner(planner);
    }

    public void exit(){
        Stage stage = (Stage) exitApp.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
