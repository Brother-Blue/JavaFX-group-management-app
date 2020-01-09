package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json_reader_writer.JsonReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main extends Application {

    private static JsonReader reader = new JsonReader();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml-files/dashboard.fxml"));
        primaryStage.setTitle("Group 13's Project Manager");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

    }

    public static void main(String[] args) throws IOException, ParseException {
        launch(args);
    }
}
