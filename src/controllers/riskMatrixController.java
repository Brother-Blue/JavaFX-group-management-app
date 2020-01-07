package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json_reader_writer.JsonReader;
import member_manager.RiskMatrix;

import java.io.IOException;
import java.util.ArrayList;

public class riskMatrixController extends MainController {

    public void visualRisks(){
        JsonReader jsonReader = new JsonReader();
        ArrayList<RiskMatrix> risks = new ArrayList<>();
        risks = jsonReader.loadRiskMatrix();

        //todo add fxid things and make em display this amazing arraylist.
    }

    @Override
    public void back(ActionEvent event) throws IOException {

        Parent backMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/projectOverview.fxml"));
        Scene backMemberScene = new Scene(backMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(backMemberScene);
        window.show();
    }
}
