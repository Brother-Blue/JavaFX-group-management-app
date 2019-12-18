package controllers;

import id_generator.GeneratorMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import member_manager.Member;

import java.io.IOException;
import java.util.ArrayList;

public class userInfoController extends MainController {
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart barChart;
    @FXML
    private TextField searchForID;
    @FXML
    private Button searchForIDButton;
    @FXML
    private Button showHours;
    @FXML
    private Button showSalary;
    @FXML
    private Button submitButton;
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
    private ComboBox compareHoursSalaryDropdown;
    @FXML
    private ComboBox memberIdDropdown;

    public void initialize() {
        ObservableList<String> memberDropdownItems = FXCollections.observableArrayList();
        for (Member member : planner.members) {
            memberDropdownItems.add(Integer.toString(member.getId()) + " : " + member.getFirstName() + " " + member.getLastName());
        }
        memberIdDropdown.setItems(memberDropdownItems);
    }

    public void setText(ActionEvent actionEvent) {
        searchForID.setText(memberIdDropdown.getValue().toString().substring(0, 5));
    }

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

            ObservableList<String> optionsIds = FXCollections.observableArrayList();
            for (Member member : planner.members) {
                if (member.getId() != Integer.parseInt(searchForID.getText())) {
                    optionsIds.add(Integer.toString(member.getId()) + " : " + member.getFirstName() + " " + member.getLastName().substring(0, 1) + ".");
                }
            }
            compareUserDropdown.setItems(optionsIds);

            ObservableList<String> optionsHoursSalary = FXCollections.observableArrayList();
            optionsHoursSalary.add("Hours");
            optionsHoursSalary.add("Salaries");
            compareHoursSalaryDropdown.setItems(optionsHoursSalary);

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
                //AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Member found, loading information...");
                revealName.setText(memberName);
                revealID.setText(Integer.toString(searchedID));
                System.out.println("Name set to: " + memberName);
                System.out.println("ID set to: " + searchedID);

                showHours.setDisable(false);
                showSalary.setDisable(false);
                submitButton.setDisable(false);
                compareHoursSalaryDropdown.setDisable(false);
                compareUserDropdown.setDisable(false);
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
        pieChart.getData().clear();

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        for (Member member : planner.members) {
            int hours = (int) planner.getTotalHours(member.getId());
            String memberID = member.getFirstName() + ": " + hours + " hours";
            chartData.add(new PieChart.Data(memberID, hours));
        }

        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(false);
        pieChart.setTitle("Hours worked");
        pieChart.setData(chartData);

        pieChart.getData().stream().forEach(data -> {
            Tooltip valuesToolTip = new Tooltip();
            valuesToolTip.setText(data.getName());
            Tooltip.install(data.getNode(), valuesToolTip);
            valuesToolTip.setShowDelay(Duration.ZERO);
        });

        //draw graph
        String name = getName();
        barChart.setTitle("Hours worked by: " + name);
        xAxis.setLabel("Week");
        yAxis.setLabel("Hours");

        barChart.getData().clear();
        barChart.autosize();

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

        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        barChart.getData().add(dataSeries);

        //add in info
        userInfoDescription.setText(name + "'s total hours are " + planner.getTotalHours(Integer.parseInt(searchForID.getText())) + " hours. Compared to the total group average of " + avgHours + ".");
    }

    public void showSalary(ActionEvent event) throws IOException {

        //do pieChart
        pieChart.getData().clear();

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        double totalSalary = 0;
        for (Member member : planner.members) {
            totalSalary = member.getSalary() * planner.getTotalHours(member.getId());
            String memberID = member.getFirstName() + ": " + totalSalary + " SEK";
            chartData.add(new PieChart.Data(memberID, totalSalary));
        }

        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(true);
        pieChart.setTitle("Salary Earned");
        pieChart.setData(chartData);

        pieChart.getData().stream().forEach(data -> {
            Tooltip valuesToolTip = new Tooltip();
            valuesToolTip.setText(data.getName());
            Tooltip.install(data.getNode(), valuesToolTip);
            valuesToolTip.setShowDelay(Duration.ZERO);
        });

        //Do barChart
        String name = getName();
        barChart.setTitle("Salaries earned by: " + name);
        xAxis.setLabel("Week");
        yAxis.setLabel("Amount (SEK)");

        barChart.getData().clear();
        barChart.autosize();

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

        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        barChart.getData().add(dataSeries);

        double avgSalary = 0;
        for (Member member : planner.members) {
            avgSalary = Math.round((avgSalary + (member.getSalary() * planner.getTotalHours(member.getId()))) / planner.members.size());
        }

        //add in info
        userInfoDescription.setText(name + "'s total salary is " + planner.getTotalHours(Integer.parseInt(searchForID.getText()))*salary + "sek. Compared to the total group average of " + avgSalary + ".");
    }

    public void compareUsers(ActionEvent actionEvent) {
        int firstID = Integer.parseInt(searchForID.getText());
        String secondID = (String) compareUserDropdown.getValue();
        int compareID = Integer.parseInt(secondID.substring(0, 5));
        String compareType = (String) compareHoursSalaryDropdown.getValue();

        System.out.println(firstID + ", " + secondID); //debuggers
        System.out.println(firstID + ", " + compareID);

        Member member1 = null;
        Member member2 = null;

        for (Member member : planner.members) {
            if (member.getId() == firstID) {
                member1 = member;
            } else if (member.getId() == compareID) {
                member2 = member;
            }
        }

        System.out.println(member1);
        System.out.println(member2);

        //draw piechart for both members.
        pieChart.getData().clear();
        pieChart.autosize();

        //lists for hours pie chart
        ObservableList<PieChart.Data> hoursMembers = FXCollections.observableArrayList();

        hoursMembers.add(new PieChart.Data(member1.getFirstName(), planner.getTotalHours(member1.getId())));
        hoursMembers.add(new PieChart.Data(member2.getFirstName(), planner.getTotalHours(member2.getId())));

        //lists for salaries pie chart
        ObservableList<PieChart.Data> salaryMembers = FXCollections.observableArrayList();

        salaryMembers.add(new PieChart.Data(member1.getFirstName(), planner.getTotalHours(member1.getId()) * member1.getSalary()));
        salaryMembers.add(new PieChart.Data(member2.getFirstName(), planner.getTotalHours(member2.getId()) * member2.getSalary()));

        //data series for hours bar chart
        XYChart.Series hoursSeriesMember1 = new XYChart.Series();
            hoursSeriesMember1.setName(member1.getFirstName());
        XYChart.Series hoursSeriesMember2 = new XYChart.Series();
            hoursSeriesMember2.setName(member2.getFirstName());
        XYChart.Series salarySeriesMember1 = new XYChart.Series();
            salarySeriesMember1.setName(member1.getFirstName());
        XYChart.Series salarySeriesMember2 = new XYChart.Series();
            salarySeriesMember2.setName(member2.getFirstName());

        ArrayList<Double> hoursWorkedMember1 = planner.getHours(member1.getId());
        ArrayList<Double> hoursWorkedMember2 = planner.getHours(member2.getId());

        for (int i = 0; i < hoursWorkedMember1.size(); i++) {
            hoursSeriesMember1.getData().add(new XYChart.Data<>(Integer.toString(i+1), hoursWorkedMember1.get(i)));
            salarySeriesMember1.getData().add(new XYChart.Data<>(Integer.toString(i+1), (hoursWorkedMember1.get(i) * member1.getSalary())));
        }

        for (int i = 0; i < hoursWorkedMember2.size(); i++) {
            hoursSeriesMember2.getData().add(new XYChart.Data<>(Integer.toString(i+1), hoursWorkedMember2.get(i)));
            salarySeriesMember2.getData().add(new XYChart.Data<>(Integer.toString(i+1), hoursWorkedMember2.get(i) * member2.getSalary()));
        }

        //draw graphs
        if (!compareType.isEmpty()) {
            if (compareType.equalsIgnoreCase("hours")) {
                pieChart.getData().clear();
                pieChart.setLegendVisible(true);
                pieChart.setLabelsVisible(false);
                pieChart.setTitle("Compared Hours");
                pieChart.setData(hoursMembers);

                pieChart.getData().stream().forEach(data -> {
                    Tooltip valuesToolTip = new Tooltip();
                    valuesToolTip.setText(data.getName() + "\n" + data.getPieValue() + " hours");
                    Tooltip.install(data.getNode(), valuesToolTip);
                    valuesToolTip.setShowDelay(Duration.ZERO);
                });

                barChart.getData().clear();
                barChart.autosize();
                xAxis.setAnimated(false);
                yAxis.setAnimated(false);
                barChart.setTitle("Compared Hours");
                barChart.getData().addAll(hoursSeriesMember1, hoursSeriesMember2);

                userInfoDescription.setText(member1.getFirstName() + "'s total hours worked are " + planner.getTotalHours(member1.getId()) +
                        "\nWhile " + member2.getFirstName() + "'s total hours worked are " + planner.getTotalHours(member2.getId()) + ".");
            }
            if (compareType.equalsIgnoreCase("salaries")) {
                pieChart.getData().clear();
                pieChart.setLegendVisible(true);
                pieChart.setLabelsVisible(false);
                pieChart.setTitle("Compared Salaries");
                pieChart.setData(salaryMembers);

                pieChart.getData().stream().forEach(data -> {
                    Tooltip valuesToolTip = new Tooltip();
                    valuesToolTip.setText(data.getName() + "\n" + data.getPieValue() + "SEK");
                    Tooltip.install(data.getNode(), valuesToolTip);
                    valuesToolTip.setShowDelay(Duration.ZERO);
                });

                barChart.getData().clear();
                barChart.autosize();
                xAxis.setAnimated(false);
                yAxis.setAnimated(false);
                barChart.setTitle("Compared Salaries");
                barChart.getData().addAll(salarySeriesMember1, salarySeriesMember2);

                userInfoDescription.setText(member1.getFirstName() + "'s total earned salary is " + planner.getTotalHours(member1.getId()) * member1.getSalary() + "SEK" +
                        "\nWhereas " + member2.getFirstName() + "'s total earned salary is " + planner.getTotalHours(member2.getId()) * member2.getSalary() + "SEK.");
            }
        }
    }
}
