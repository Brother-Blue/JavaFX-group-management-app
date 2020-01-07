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

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class riskMatrixController extends MainController {

    @FXML
    private javafx.scene.control.Label e11;
    @FXML
    private javafx.scene.control.Label e12;
    @FXML
    private javafx.scene.control.Label e13;
    @FXML
    private javafx.scene.control.Label e14;
    @FXML
    private javafx.scene.control.Label e15;
    @FXML
    private javafx.scene.control.Label l22;
    @FXML
    private javafx.scene.control.Label l23;
    @FXML
    private javafx.scene.control.Label l21;
    @FXML
    private javafx.scene.control.Label l24;
    @FXML
    private javafx.scene.control.Label l25;
    @FXML
    private javafx.scene.control.Label l32;
    @FXML
    private javafx.scene.control.Label l34;
    @FXML
    private javafx.scene.control.Label l44;
    @FXML
    private javafx.scene.control.Label l33;
    @FXML
    private javafx.scene.control.Label l45;
    @FXML
    private javafx.scene.control.Label l31;
    @FXML
    private javafx.scene.control.Label l42;
    @FXML
    private javafx.scene.control.Label l43;
    @FXML
    private javafx.scene.control.Label l41;
    @FXML



    public void visualRisks() {
        JsonReader jsonReader = new JsonReader();
        ArrayList<RiskMatrix> risks;
        risks = jsonReader.loadRiskMatrix();
        e11.setText(risks.get(0).getRiskName());
        l21.setText(risks.get(0).getVeryLikely());
        l31.setText(risks.get(0).getPossible());
        l41.setText(risks.get(0).getUnlikely());

        e12.setText(risks.get(1).getRiskName());
        l22.setText(risks.get(1).getVeryLikely());
        l32.setText(risks.get(1).getPossible());
        l42.setText(risks.get(1).getUnlikely());

        e13.setText(risks.get(2).getRiskName());
        l23.setText(risks.get(2).getVeryLikely());
        l33.setText(risks.get(2).getPossible());
        l43.setText(risks.get(2).getUnlikely());

        e14.setText(risks.get(3).getRiskName());
        l24.setText(risks.get(3).getVeryLikely());
        l34.setText(risks.get(3).getPossible());
        l44.setText(risks.get(3).getUnlikely());

        e15.setText(risks.get(4).getRiskName());
        l25.setText(risks.get(4).getVeryLikely());
        l45.setText(risks.get(4).getUnlikely());
        l34.setText(risks.get(4).getPossible());




    }
    public void initialize () {
        visualRisks();
    }


        //todo add fxid things and make em display this amazing arraylist.

    @Override
    public void back(ActionEvent event) throws IOException {

        Parent backMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/projectOverview.fxml"));
        Scene backMemberScene = new Scene(backMemberParent);


        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(backMemberScene);
        window.show();
    }
}
