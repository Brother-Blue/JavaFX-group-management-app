package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import json_reader_writer.JsonReader;
import member_manager.RiskMatrix;

import java.io.IOException;
import java.util.ArrayList;

public class riskMatrixController extends MainController {

    @FXML
    private AnchorPane event11;
    @FXML
    private AnchorPane event12;
    @FXML
    private AnchorPane event13;
    @FXML
    private AnchorPane event14;
    @FXML
    private AnchorPane event15;
    @FXML
    private AnchorPane likely21;
    @FXML
    private AnchorPane likely22;
    @FXML
    private AnchorPane likely23;
    @FXML
    private AnchorPane likely24;
    @FXML
    private AnchorPane likely25;
    @FXML
    private AnchorPane likely31;
    @FXML
    private AnchorPane likely32;
    @FXML
    private AnchorPane likely33;
    @FXML
    private AnchorPane likely34;
    @FXML
    private AnchorPane likely35;
    @FXML
    private AnchorPane likely41;
    @FXML
    private AnchorPane likely42;
    @FXML
    private AnchorPane likely43;
    @FXML
    private AnchorPane likely44;
    @FXML
    private AnchorPane likely45;


    public void visualRisks(){
        JsonReader jsonReader = new JsonReader();
        ArrayList<RiskMatrix> risks = new ArrayList<>();
        risks = jsonReader.loadRiskMatrix();
        event11.setAccessibleText(risks.get(0).getRiskName());

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
