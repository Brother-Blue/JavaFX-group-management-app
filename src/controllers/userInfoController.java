package controllers;

import id_generator.GeneratorMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import member_manager.Member;

import java.io.IOException;
import java.util.ArrayList;

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
    @FXML
    private Text userInfoDescription;
    @FXML
    private PieChart pieChart;
    @FXML
    private ComboBox compareUserDropdown;
    @FXML
    private Button submitButton;

    // Code goes into here, whatever you want to happen when you search.
    public void search() {

        ObservableList<String> options = FXCollections.observableArrayList();
        for (Member member : planner.members) {
            options.add(Integer.toString(member.getId()));
        }
        compareUserDropdown.setItems(options);

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
        //draw pie chart
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        for (Member member : planner.members) {
            int hours = (int) planner.getTotalHours(member.getId());
            String memberID = member.getFirstName() + ": " + hours + "hours";
            chartData.add(new PieChart.Data(memberID, hours));
        }

        pieChart.setLabelLineLength(30);
        pieChart.setLegendVisible(true);
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setTitle("Hours worked");
        pieChart.setData(chartData);

        //draw graph
        String name = getName();
        chart.setTitle("Hours worked by: " + name);
        xAxis.setLabel("Week");
        yAxis.setLabel("Hours");

        chart.getData().clear();
        chart.autosize();

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Hours Worked");

        double totalHours = 0;

        for (Member member : planner.members) {
            totalHours = totalHours + planner.getTotalHours(member.getId());
        }

        double avgHours = Math.round(totalHours / planner.members.size());

        ArrayList<Double> hoursWorked = planner.getHours(Integer.parseInt(searchForID.getText()));
        for (int i = 0; i < hoursWorked.size(); i++) {
            double hours = 0;
            hours = hoursWorked.get(i);
            dataSeries.getData().add(new XYChart.Data<>(Integer.toString(i+1), hours));
        }
        chart.getData().add(dataSeries);

        //add in info
        userInfoDescription.setText("The member's total hours are " + planner.getTotalHours(Integer.parseInt(searchForID.getText())) + " hours. Compared to the total group average of " + avgHours + ".");
    }

    public void showSalary(ActionEvent event) throws IOException {

        //do pieChart
        pieChart.getData().clear();
        pieChart.autosize();

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        double totalSalary = 0;
        for (Member member : planner.members) {
            totalSalary = member.getSalary() * planner.getTotalHours(member.getId());
            String memberID = member.getFirstName() + ": " + totalSalary + " SEK";
            chartData.add(new PieChart.Data(memberID, totalSalary));
        }

        pieChart.setLabelLineLength(20);
        pieChart.setLegendVisible(true);
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setTitle("Salary Earned");
        pieChart.setData(chartData);

        //Do barChart
        String name = getName();
        chart.setTitle("Salaries earned by: " + name);
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        chart.getData().clear();
        chart.autosize();

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Salary Earned");

        ArrayList<Double> hoursWorked = planner.getHours(Integer.parseInt(searchForID.getText()));

        double salary = 0;
        for (Member member : planner.members) {
            if (Integer.parseInt(searchForID.getText()) == member.getId()) {
                salary = member.getSalary();
            }
        }

        for (int i = 0; i < hoursWorked.size(); i++) {
            double total = salary*hoursWorked.get(i);
            dataSeries.getData().add(new XYChart.Data<>(Integer.toString(i), total));
        }

        chart.getData().add(dataSeries);

        double avgSalary = 0;
        for (Member member : planner.members) {
            avgSalary = Math.round((avgSalary + (member.getSalary() * planner.getTotalHours(member.getId()))) / planner.members.size());
        }

        //add in info
        userInfoDescription.setText("The member's total salary is " + planner.getTotalHours(Integer.parseInt(searchForID.getText()))*salary + "sek. Compared to the total group average of " + avgSalary + ".");
    }

    public void compareUsers(ActionEvent actionEvent) {
        int firstID = Integer.parseInt(searchForID.getText());
        String compareID = ((String) compareUserDropdown.getValue());
        System.out.println(firstID + ", " + compareID);
    }
}
