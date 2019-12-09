package sample;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class PopUp {


    public static void display(String label, String title)
    {
        Stage popUpWindow = new Stage();

        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle(title);


        Label label1= new Label(label);


        Button button1= new Button("Close");


        button1.setOnAction(e -> popUpWindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popUpWindow.setScene(scene1);

        popUpWindow.showAndWait();

    }

}
