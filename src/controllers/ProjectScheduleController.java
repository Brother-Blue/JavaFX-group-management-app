package controllers;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import member_manager.Member;
import member_manager.Schedule;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectScheduleController extends MainController implements Initializable {

    @FXML
    private Text headline;
    @FXML
    private TableView<Schedule> tableView;
    @FXML
    private TableColumn<Schedule, String> id;
    @FXML
    private TableColumn<Schedule, String> task;
    @FXML
    private TableColumn<Schedule, LocalDate> startDate;
    @FXML
    private TableColumn<Schedule, LocalDate> finishDate;
    @FXML
    private TableColumn<Schedule, String> duration;
    @FXML
    private TableColumn<Schedule, String> resources;

    @FXML
    public void backProject(ActionEvent event) throws IOException {

        Parent backProjectMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/projectOverview.fxml"));
        Scene backProjectMemberScene = new Scene(backProjectMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(backProjectMemberScene);
        window.show();
    }

    @FXML
    public void loadAnalysis(ActionEvent event) throws IOException {

        id.setCellValueFactory(new PropertyValueFactory<Schedule, String>("id"));
        task.setCellValueFactory(new PropertyValueFactory<Schedule, String>("task"));
        startDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("startDate"));
        finishDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("finishDate"));
        duration.setCellValueFactory(new PropertyValueFactory<Schedule, String>("duration"));
        resources.setCellValueFactory(new PropertyValueFactory<Schedule, String>("resources"));

        headline.setText("Analysis");

        tableView.setItems(getAnalysis());
    }

    @FXML
    public void loadUI(ActionEvent event) throws IOException {

        id.setCellValueFactory(new PropertyValueFactory<Schedule, String>("id"));
        task.setCellValueFactory(new PropertyValueFactory<Schedule, String>("task"));
        startDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("startDate"));
        finishDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("finishDate"));
        duration.setCellValueFactory(new PropertyValueFactory<Schedule, String>("duration"));
        resources.setCellValueFactory(new PropertyValueFactory<Schedule, String>("resources"));

        headline.setText("UI Design");

        tableView.setItems(getUIDesign());
    }

    @FXML
    public void loadDevelopment(ActionEvent event) throws IOException {
        id.setCellValueFactory(new PropertyValueFactory<Schedule, String>("id"));
        task.setCellValueFactory(new PropertyValueFactory<Schedule, String>("task"));
        startDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("startDate"));
        finishDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("finishDate"));
        duration.setCellValueFactory(new PropertyValueFactory<Schedule, String>("duration"));
        resources.setCellValueFactory(new PropertyValueFactory<Schedule, String>("resources"));

        headline.setText("Development");

        tableView.setItems(getDevelopment());
    }

    @FXML
    public void loadCompletion(ActionEvent event) throws IOException {
        id.setCellValueFactory(new PropertyValueFactory<Schedule, String>("id"));
        task.setCellValueFactory(new PropertyValueFactory<Schedule, String>("task"));
        startDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("startDate"));
        finishDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("finishDate"));
        duration.setCellValueFactory(new PropertyValueFactory<Schedule, String>("duration"));
        resources.setCellValueFactory(new PropertyValueFactory<Schedule, String>("resources"));

        headline.setText("Completion");

        tableView.setItems(getCompletion());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<Schedule, String>("id"));
        task.setCellValueFactory(new PropertyValueFactory<Schedule, String>("task"));
        startDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("startDate"));
        finishDate.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("finishDate"));
        duration.setCellValueFactory(new PropertyValueFactory<Schedule, String>("duration"));
        resources.setCellValueFactory(new PropertyValueFactory<Schedule, String>("resources"));

        headline.setText("Analysis");

        tableView.setItems(getAnalysis());
    }

    public ObservableList<Schedule> getAnalysis(){
        ObservableList<Schedule> analysis = FXCollections.observableArrayList();

        analysis.add(new Schedule("1", "Analysis", LocalDate.of(2019, Month.NOVEMBER, 04),
                LocalDate.of(2019, Month.NOVEMBER, 17), "13 days",
                "Clementine, Hugo, Linus I, Linus Å, Christian, Altug, Hjalmar"));

        analysis.add(new Schedule("2", "Planning Meetings", LocalDate.of(2019, Month.NOVEMBER, 11),
                LocalDate.of(2019, Month.NOVEMBER, 14), "4 days",
                "Clementine, Hugo, Linus I, Linus Å, Christian, Altug, Hjalmar"));

        analysis.add(new Schedule("3", "Create Class Diagram", LocalDate.of(2019, Month.NOVEMBER, 13),
                LocalDate.of(2019, Month.NOVEMBER, 15), "3 days",
                "Christian, Linus I, Clementine, Hjalmar"));

        analysis.add(new Schedule("4", "Analysis Finished", LocalDate.of(2019, Month.NOVEMBER, 17),
                LocalDate.of(2019, Month.NOVEMBER, 19), "1 day", ""));

        return analysis;
    }

    public ObservableList<Schedule> getUIDesign(){

        ObservableList<Schedule> UIDesign = FXCollections.observableArrayList();

        UIDesign.add(new Schedule("5", "UI Design", LocalDate.of(2019, Month.NOVEMBER, 18),
                LocalDate.of(2019, Month.DECEMBER, 22), "35 days",
                "Clementine, Hugo, Linus I, Christian"));

        UIDesign.add(new Schedule("6", "Migration to JavaFX", LocalDate.of(2019, Month.NOVEMBER, 18),
                LocalDate.of(2019, Month.DECEMBER, 01), "14 days",
                "Clementine, Linus I, Christian"));

        UIDesign.add(new Schedule("7", "Create FXML Files", LocalDate.of(2019, Month.NOVEMBER, 22),
                LocalDate.of(2019, Month.DECEMBER, 22), "29 days",
                "Christian, Linus I, Clementine, Hjalmar"));

        UIDesign.add(new Schedule("8", "Create Controllers", LocalDate.of(2019, Month.NOVEMBER, 22),
                LocalDate.of(2019, Month.DECEMBER, 22), "29 day",
                "Cleemntine, Linus I, Christian, Hugo"));

        UIDesign.add(new Schedule("9", "UI Design Finished", LocalDate.of(2019, Month.DECEMBER, 22),
                LocalDate.of(2019, Month.DECEMBER, 22), "1 day", ""));

        return UIDesign;
    }

    public ObservableList<Schedule> getDevelopment(){

        ObservableList<Schedule> development = FXCollections.observableArrayList();

        development.add(new Schedule("10", "Development", LocalDate.of(2019, Month.NOVEMBER, 18),
                LocalDate.of(2020, Month.JANUARY, 10), "54 days",
                "Clementine, Hugo, Linus I, Linus Å, Christian, Altug, Hjalmar"));

        development.add(new Schedule("11", "Develop Methods", LocalDate.of(2019, Month.NOVEMBER, 18),
                LocalDate.of(2020, Month.JANUARY, 10), "54 days",
                "Clementine, Hugo, Linus I, Linus Å, Christian, Altug, Hjalmar"));

        development.add(new Schedule("12", "Implement Json", LocalDate.of(2019, Month.NOVEMBER, 25),
                LocalDate.of(2019, Month.DECEMBER, 07), "13 days",
                "Linus Å, Christian, Altug, Hjalmar"));

        development.add(new Schedule("13", "Finish Coding", LocalDate.of(2019, Month.NOVEMBER, 25),
                LocalDate.of(2020, Month.JANUARY, 10), "54 days",
                "Clementine, Hugo, Linus I, Linus Å, Christian, Altug, Hjalmar"));

        development.add(new Schedule("14", "Development Finished", LocalDate.of(2020, Month.JANUARY, 10),
                LocalDate.of(2020, Month.JANUARY, 10), "1 day", ""));

        return development;
    }

    public ObservableList<Schedule> getCompletion(){

        ObservableList<Schedule> completion = FXCollections.observableArrayList();

        completion.add(new Schedule("15", "Completion", LocalDate.of(2019, Month.DECEMBER, 17),
                LocalDate.of(2020, Month.JANUARY, 10), "25 days",
                "Clementine, Hugo, Linus I, Linus Å, Christian, Altug, Hjalmar"));

        completion.add(new Schedule("16", "Create Executable", LocalDate.of(2020, Month.JANUARY, 07),
                LocalDate.of(2020, Month.JANUARY, 10), "4 days",
                "Hugo, Linus Å "));

        completion.add(new Schedule("17", "Final Report", LocalDate.of(2019, Month.DECEMBER, 17),
                LocalDate.of(2020, Month.JANUARY, 10), "25 days",
                "Clementine, Hugo, Linus I, Linus Å, Christian, Altug, Hjalmar"));

        return completion;
    }

}

