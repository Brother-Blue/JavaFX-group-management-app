package controllers;

import id_generator.GeneratorMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import json_reader_writer.JsonReader;
import member_manager.Member;
import member_manager.Milestone;
import member_manager.Planner;

import java.io.IOException;

public class userInfoController extends MainController {
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart chart;
    @FXML
    private TextField searchForID;
    @FXML
    private Button searchForIDButton;
    @FXML
    private Text revealName;
    @FXML
    private Text revealID;


    // Code goes into here, whatever you want to happen when you search.
    public void search() {
        Window owner = searchForIDButton.getScene().getWindow();
        int searchedID = 0;
        boolean IDValid = true;
        boolean IDFound = false;

        if (searchForID.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Info", "Search field must be filled in!");
            System.out.println("Search failed (Empty search).");
            IDValid = false;
        } else if (GeneratorMain.isParsable(searchForID.getText())) {
            searchedID = Integer.parseInt(searchForID.getText());

            if (Integer.parseInt(searchForID.getText()) < 10000) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Error", "ID's are positive 5-digit integers!");
                System.out.println("Search failed (Invalid ID format, less than 5 digits OR negative input).");
                IDValid = false;
            } else if (Integer.parseInt(searchForID.getText()) > 99999) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Error", "ID's are positive 5-digit integers!");
                System.out.println("Search failed (Invalid ID format, more than 5 digits");
                IDValid = false;
            }
        } else {
            if (!GeneratorMain.isParsable(searchForID.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "ID's must be 5-digit integers!");
                System.out.println("Search failed (Invalid ID format, non-number characters).");
                IDValid = false;
            }
        }
        if (IDValid) {
            //Input the team member's information into the page
            System.out.println(">> Debugger");
            System.out.println(planner.members);

            String memberName = "";
            for (Member member : planner.members) {
                System.out.println(member.getId());
                if (searchedID == member.getId()) {
                    IDFound = true;
                    memberName = member.getFirstName().concat(" ").concat(member.getLastName());
                }
            }

            if (IDFound) {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Member found, loading information...");
                revealName.setText(memberName);
                revealID.setText(Integer.toString(searchedID));
                System.out.println("Name set to: " + memberName);
                System.out.println("ID set to: " + searchedID);
            } else {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Error", "Member ID does not yet exist.");
            }
        }
    }

    public void editMember(ActionEvent event) throws IOException{

        Parent editMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/editMember.fxml"));
        Scene editMemberScene = new Scene(editMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(editMemberScene);
        window.show();

    }

    public String getName() {
        int ID = 0;
        if (GeneratorMain.isParsable(searchForID.getText())) {
            ID = Integer.parseInt(searchForID.getText());
        }
        String name = "";

        for (Member member : planner.members) {
            if (ID == member.getId()) {
                name = member.getFirstName() + " " + member.getLastName();
            }
        }
        return name;
    }
    public void showHours(ActionEvent event) throws IOException {
        //doCode();
        String name = getName();
        chart.setTitle("Hours worked by: " + name);
        xAxis.setLabel("Week");
        yAxis.setLabel("Hours");

        chart.getData().clear();
        chart.autosize();

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Test");

        int iteration = 1;
        for (Milestone milestone : planner.milestones) {
            double hours = planner.getHours(Integer.parseInt(searchForID.getText()));
            dataSeries.getData().add(new XYChart.Data(Integer.toString(iteration), (int) hours));
            iteration++;
        }

        /*
        dataSeries.getData().add(new XYChart.Data<>("1", 2));
        dataSeries.getData().add(new XYChart.Data<>("2", 4));
        dataSeries.getData().add(new XYChart.Data<>("3", 8));
        dataSeries.getData().add(new XYChart.Data<>("4", 3));
        dataSeries.getData().add(new XYChart.Data<>("5", 7));
        dataSeries.getData().add(new XYChart.Data<>("6", 9));

         */



        chart.getData().add(dataSeries);
    }

    public void showSalary(ActionEvent event) throws IOException {
        //doCode();
        String name = getName();
        chart.setTitle("Salaries earned by: " + name);
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        chart.getData().clear();
        chart.autosize();

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Test");

        dataSeries.getData().add(new XYChart.Data<>("1", 145));
        dataSeries.getData().add(new XYChart.Data<>("2", 298));
        dataSeries.getData().add(new XYChart.Data<>("3", 1092));
        dataSeries.getData().add(new XYChart.Data<>("4", 569));
        dataSeries.getData().add(new XYChart.Data<>("5", 238));
        dataSeries.getData().add(new XYChart.Data<>("6", 360));

        chart.getData().add(dataSeries);
    }


}
