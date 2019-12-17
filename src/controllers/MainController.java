package controllers;

import id_generator.GeneratorMain;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import json_reader_writer.JsonReader;
import member_manager.Member;
import member_manager.Milestone;
import member_manager.Planner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

// TODO: FIX ALL ENCAPSULATION


public class MainController {

    private static JsonReader reader = new JsonReader();
    static Planner planner = reader.loadPlanner();

    // This code is for all back buttons that go back to the dashboard.
    @FXML
    public void back(ActionEvent event) throws IOException {

        Parent backMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/dashboard.fxml"));
        Scene backMemberScene = new Scene(backMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(backMemberScene);
        window.show();
    }

}
